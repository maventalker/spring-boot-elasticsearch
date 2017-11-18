## SpringBoot+ spring-data-elasticsearch + elasticsearch
通过三种方式操作elasticsearch
- 原生的TransportClient方式，还有一种是NodeClient形式，本章节不涉及，具体区别可网络检索下。 CommandOnStart启动后即加载的类中有涉及
- BlackGreyDataRepository方式，集成度较高，基本满足需要，包括索引的CRUD。BlackGreyDataService有涉及
- ElasticsearchTemplate方式，方法众多，比较灵活。IndexerService中有采用bulk的方式来批量操作索引
- RestClient客户端，es 5+版本后提供的java client。spring data elasticsearch目前尚不支持es 5+

···
TODO ：
  完善搜索相关功能：汉字搜索，拼音（全拼，首拼）搜索，近义词搜索，自动纠错
···
>项目构建基于profiles完完成了多环境的切换。

##### 获取更多内容，请关注公众号
![image](https://github.com/backkoms/simplemall/blob/develop/getqrcode.jpeg?raw=true)
