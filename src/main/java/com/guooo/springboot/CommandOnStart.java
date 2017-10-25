/**
 * Project Name:spring-boot-es
 * File Name:CommandOnStart.java
 * Package Name:com.guooo.springboot
 * Date:2017年10月25日下午3:31:01
 *
*/

package com.guooo.springboot;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;

import org.elasticsearch.action.bulk.BulkRequestBuilder;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * ClassName:CommandOnStart <br/>
 * Function: 原生的方式操作es<br/>
 * Date: 2017年10月25日 下午3:31:01 <br/>
 * 
 * @author guooo
 * @version
 * @since JDK 1.6
 * @see
 */
@Component
public class CommandOnStart implements CommandLineRunner {

    @Override
    public void run(String... arg0) throws Exception {
        Settings settings = Settings.settingsBuilder().put("cluster.name", "elasticsearch").build();

        try {

            Client client = TransportClient.builder().settings(settings).build()
                    .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("192.168.1.105"), 9300));

            // 批量创建索引  
            BulkRequestBuilder bulkRequest = client.prepareBulk();
            Map<String, Object> map = new HashMap<>();
            map.put("name", "guooo");
            map.put("sex", "男");
            map.put("微信公众号", "usgrouping");

            IndexRequest request = client.prepareIndex("dept", "leanstartup", "goooooo").setSource(map).request();
            bulkRequest.add(request);
            BulkResponse bulkResponse = bulkRequest.execute().actionGet();
            if (bulkResponse.hasFailures()) {
                System.out.println("批量创建索引错误！");
            }
            client.close();
            System.out.println("批量创建索引成功");
        } catch (UnknownHostException e) {
            // TODO Auto-generated catch block  
            e.printStackTrace();
        }

    }

}
