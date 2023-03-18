package com.masamiaoi.elasticsearch.service;

import com.alibaba.fastjson.JSON;
import com.masamiaoi.elasticsearch.Constant.Constant;
import com.masamiaoi.elasticsearch.po.UserPO;
import com.masamiaoi.elasticsearch.vo.UserCityVO;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.admin.indices.create.CreateIndexRequest;
import org.elasticsearch.action.admin.indices.create.CreateIndexResponse;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.TermQueryBuilder;
import org.elasticsearch.rest.RestStatus;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.Aggregations;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.elasticsearch.search.aggregations.bucket.terms.TermsAggregationBuilder;
import org.elasticsearch.search.aggregations.metrics.Avg;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @author: MASAMIAOI
 * @description: 用户实现类
 * @date: 2023/3/18 12:27
 * @version: 1.0.0
 */
@Slf4j
@Service
public class UserService {


    @Autowired
    private RestHighLevelClient client;


    /**
     * 创建索引
     *
     * @param index 索引名称
     */
    public boolean createUserIndex(String index) throws IOException {
        CreateIndexRequest createIndexRequest = new CreateIndexRequest(index);
        /*
            number_of_shards 是指索引要做多少个分片，只能在创建索引时指定，后期无法修改。
            number_of_replicas 是指每个分片有多少个副本，后期可以动态修改
        */
        createIndexRequest.settings(Settings.builder()
                .put("index.number_of_shards", 1)
                .put("index.number_of_replicas", 0)
        );
        /*
            type: 映射类型
            source： 映射元
        */
        createIndexRequest.mapping("_doc", "{\n" +
                "  \"properties\": {\n" +
                "    \"city\": {\n" +
                "      \"type\": \"keyword\"\n" +
                "    },\n" +
                "    \"sex\": {\n" +
                "      \"type\": \"keyword\"\n" +
                "    },\n" +
                "    \"name\": {\n" +
                "      \"type\": \"keyword\"\n" +
                "    },\n" +
                "    \"id\": {\n" +
                "      \"type\": \"keyword\"\n" +
                "    },\n" +
                "    \"age\": {\n" +
                "      \"type\": \"integer\"\n" +
                "    }\n" +
                "  }\n" +
                "}", XContentType.JSON);
        CreateIndexResponse createIndexResponse = client.indices().create(createIndexRequest, RequestOptions.DEFAULT);
        return createIndexResponse.isAcknowledged();
    }

    /**
     * 删除索引
     *
     * @param index 索引名称
     */
    public Boolean deleteUserIndex(String index) throws IOException {
        DeleteIndexRequest deleteIndexRequest = new DeleteIndexRequest(index);
        AcknowledgedResponse deleteIndexResponse = client.indices().delete(deleteIndexRequest, RequestOptions.DEFAULT);
        return deleteIndexResponse.isAcknowledged();
    }

    /**
     * 创建文档
     */
    public Boolean createUserDocument(UserPO document) throws Exception {
        document.setId(UUID.randomUUID().toString());
        IndexRequest indexRequest = new IndexRequest(Constant.INDEX)
                .id(document.getId())
                .source(JSON.toJSONString(document), XContentType.JSON);
        IndexResponse indexResponse = client.index(indexRequest, RequestOptions.DEFAULT);
        return indexResponse.status().equals(RestStatus.CREATED);
    }

    /**
     * 批量创建文档
     */
    public Boolean bulkCreateUserDocument(List<UserPO> documents) throws IOException {
        BulkRequest bulkRequest = new BulkRequest();
        for (UserPO document : documents) {
            String id = UUID.randomUUID().toString();
            document.setId(id);
            IndexRequest indexRequest = new IndexRequest(Constant.INDEX)
                    .id(id)
                    .source(JSON.toJSONString(document), XContentType.JSON);
            bulkRequest.add(indexRequest);
        }
        BulkResponse bulkResponse = client.bulk(bulkRequest, RequestOptions.DEFAULT);
        return bulkResponse.status().equals(RestStatus.CREATED);
    }


    /**
     * 删除文档
     */
    public String deleteUserDocument(String id) throws Exception {
        DeleteRequest deleteRequest = new DeleteRequest(Constant.INDEX, id);
        DeleteResponse response = client.delete(deleteRequest, RequestOptions.DEFAULT);
        return response.getResult().name();
    }

    /**
     * 更新文档
     */
    public Boolean updateUserDocument(UserPO document) throws Exception {
        UserPO resultDocument = getUserDocument(document.getId());
        if (null == resultDocument) {
            return false;
        }
        UpdateRequest updateRequest = new UpdateRequest(Constant.INDEX, resultDocument.getId());
        updateRequest.doc(JSON.toJSONString(document), XContentType.JSON);
        UpdateResponse updateResponse = client.update(updateRequest, RequestOptions.DEFAULT);
        return updateResponse.status().equals(RestStatus.OK);
    }

    /**
     * 根据 id 下标查找
     */
    public UserPO getUserDocument(String id) throws IOException {
        GetRequest getRequest = new GetRequest(Constant.INDEX, id);
        GetResponse getResponse = client.get(getRequest, RequestOptions.DEFAULT);
        UserPO result = new UserPO();
        if (getResponse.isExists()) {
            String sourceAsString = getResponse.getSourceAsString();
            result = JSON.parseObject(sourceAsString, UserPO.class);
        } else {
            log.error("没有找到该 id 的文档");
            return null;
        }
        return result;
    }

    /**
     * 获取用户列表
     */
    public List<UserPO> getUserList() throws Exception {
        SearchRequest searchRequest = new SearchRequest(Constant.INDEX);
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(QueryBuilders.matchAllQuery());
        searchRequest.source(searchSourceBuilder);
        SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);
        return getSearchResult(searchResponse);
    }

    /**
     * 解析返回结果
     */
    private List<UserPO> getSearchResult(SearchResponse response) {
        SearchHit[] searchHit = response.getHits().getHits();
        List<UserPO> userDocuments = new ArrayList<>();
        for (SearchHit hit : searchHit) {
            userDocuments.add(JSON.parseObject(hit.getSourceAsString(), UserPO.class));
        }
        return userDocuments;
    }

    /**
     * 城市聚合
     */
    public List<UserCityVO> aggregationsSearchUser() throws Exception {
        SearchRequest searchRequest = new SearchRequest(Constant.INDEX);
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        TermsAggregationBuilder aggregation = AggregationBuilders.terms("by_city")
                .field("city")
                .subAggregation(AggregationBuilders
                        .avg("average_age")
                        .field("age"));
        searchSourceBuilder.aggregation(aggregation);
        searchRequest.source(searchSourceBuilder);
        SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);
        Aggregations aggregations = searchResponse.getAggregations();
        Terms byCityAggregation = aggregations.get("by_city");
        List<UserCityVO> userCityList = new ArrayList<>();
        for (Terms.Bucket buck : byCityAggregation.getBuckets()) {
            UserCityVO userCityDTO = new UserCityVO();
            userCityDTO.setCity(buck.getKeyAsString());
            userCityDTO.setCount(buck.getDocCount());
            // 获取子聚合
            Avg averageBalance = buck.getAggregations().get("average_age");
            userCityDTO.setAvgAge(averageBalance.getValue());
            userCityList.add(userCityDTO);
        }
        return userCityList;
    }

    /**
     * 根据姓名搜索用户
     */
    public List<UserPO> searchUserByCity(String city) throws Exception {
        SearchRequest searchRequest = new SearchRequest();
        searchRequest.indices(Constant.INDEX);
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        TermQueryBuilder termQueryBuilder = QueryBuilders.termQuery("city", city);
        searchSourceBuilder.query(termQueryBuilder);
        searchRequest.source(searchSourceBuilder);
        SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);
        return getSearchResult(searchResponse);
    }

}
