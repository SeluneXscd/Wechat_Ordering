<html lang="en">
<#include "../common/header.ftl">
<body>
<div id="wrapper" class="toggled">
    <#--    边栏sidebar-->
    <#include "../common/nav.ftl">
    <#--    主要内容content-->
    <div id="page-content-wrapper">
        <div class="container-fluid">
            <div class="row-fluid">
                <div class="col-md-12 column">
                    <table class="table table-hover table-bordered table-condensed">
                        <thead>
                        <tr>
                            <th>订单id</th>
                            <th>姓名</th>
                            <th>手机号</th>
                            <th>地址</th>
                            <th>金额</th>
                            <th>订单状态</th>
                            <th>支付状态</th>
                            <th>创建时间</th>
                            <th colspan="2">操作</th>
                        </tr>
                        </thead>
                        <tbody>
                        <#list orderDTOPage.content as orderDTO>
                            <tr>
                                <td>${orderDTO.orderId}</td>
                                <td>${orderDTO.buyerName}</td>
                                <td>${orderDTO.buyerPhone}</td>
                                <td>${orderDTO.buyerAddress}</td>
                                <td>${orderDTO.orderAmount}</td>
                                <td>${orderDTO.getOrderStatusEnum().message}</td>
                                <td>${orderDTO.getPayStatusEnum().message}</td>
                                <td>${orderDTO.createTime}</td>
                                <td>
                                    <a href="/sell/seller/order/detail?orderId=${orderDTO.orderId}">详情</a>
                                </td>
                                <td>
                                    <#if orderDTO.getOrderStatusEnum().message == "新订单">
                                        <a href="/sell/seller/order/cancel?orderId=${orderDTO.orderId}">取消
                                        </a>
                                    </#if>
                                </td>
                            </tr>
                        </#list>
                        </tbody>
                    </table>
                </div>
                <!-- 分页 -->
                <div class="col-md-12 column">
                    <ul class="pagination pull-right">
                        <#-- lte小于等于
                            gte 大于等于
                            lt 小于
                            gt 大于
                       -->
                        <#if currentPage lte 1>
                            <li class="disabled"><a href="#">上一页</a></li>
                        <#else>
                            <li>
                                <a href="/sell/seller/order/list?page=${currentPage - 1}&size=${size}">
                                    上一页</a>
                            </li>
                        </#if>
                        <#-- m..n 的语法就是[m,n]，进行循环 -->
                        <#-- m..<n 的语法就是[m,n)，进行循环 -->
                        <#list 1..orderDTOPage.getTotalPages() as index>
                            <#if currentPage == index>
                                <li class="disabled"><a href="#">${index}</a></li>
                            <#else>
                                <li>
                                    <a href="/sell/seller/order/list?page=${index}&size=${size}">${index}</a>
                                </li>
                            </#if>
                        </#list>
                        <#if currentPage gte orderDTOPage.getTotalPages()>
                            <li class="disabled"><a href="#">下一页</a>
                        <#else>
                            <li>
                                <a href="/sell/seller/order/list?page=${currentPage +  1}&size=${size}">
                                    下一页</a>
                            </li>
                        </#if>
                    </ul>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
