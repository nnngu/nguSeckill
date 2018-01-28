<%--&lt;%&ndash;格式化java8的LocalDatime,解决jstl不支持java8时间的问题&ndash;%&gt;--%>
<%--<%@ tag body-content="empty" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>--%>
<%--<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>--%>
<%--<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>--%>
<%--<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>--%>
<%--&lt;%&ndash;        这里是定义页面使用标签中的属性设置,<tags:localDataTime dateTime="${sk.createTIme}"/>     &ndash;%&gt;--%>
<%--<%@ attribute name="dateTime" required="true" type="java.time.LocalDateTime" %>--%>
<%--<%@ attribute name="pattern" required="false" type="java.lang.String" %>--%>
<%--&lt;%&ndash;首选判断日期时间转换规则是否存在,不存在给出默认的规则&ndash;%&gt;--%>
<%--<c:if test="${empty pattern}">--%>
    <%--<c:set var="pattern" value="yyyy-MM-dd HH:mm:ss"/>--%>
<%--</c:if>--%>
<%--<c:set var="datetime" value="${dateTime}"/>                        &lt;%&ndash; 获取jsp页面传入的【 日期时间 】,格式为【 2017-5-26T13:59:12  】 &ndash;%&gt;--%>
<%--<c:set var="time" value="${fn:substringAfter(datetime, 'T')}"/>     &lt;%&ndash;   获取页面传过来的【时间T】后面的 【  时:分:秒 】的值  &ndash;%&gt;--%>
<%--<c:set var="timeLength" value="${fn:length(time)}"/>                &lt;%&ndash;  获取页面传来的 【 时:分:秒 的长度  】 &ndash;%&gt;--%>
<%--<c:set var="generalLength" value="${fn:length('123456')}"/>         &lt;%&ndash;  这里定义了一个【Integer】类型的值,值为字符串 【123456 】的长度   &ndash;%&gt;--%>
<%--<c:set var="cloneZero" value=":00"/>                                 &lt;%&ndash;   这里设置一个值为【String】的字符串,     &ndash;%&gt;--%>
<%--&lt;%&ndash; 当  时:分:秒 不足6位的时候就说明缺少秒,我们给它自动补充 :00    &ndash;%&gt;--%>
<%--<c:if test="${timeLength lt generalLength}">--%>
    <%--<c:set var="datetimeCloneZero"--%>
           <%--value="${datetime}${cloneZero}"/>          &lt;%&ndash;  拼接页面传过来的  【 时：分 】  ,补充一个【秒数】,EL中 + 为相加,非拼接字符串   &ndash;%&gt;--%>
    <%--<c:set var="cleandDateTime"--%>
           <%--value="${fn:replace(datetimeCloneZero,'T',' ')}"/>      &lt;%&ndash;  把java8日期时间中的【 T 】给去掉,换成一个空的字符串    &ndash;%&gt;--%>
<%--</c:if>--%>
<%--&lt;%&ndash;  当页面传过来的时间大于6位时说明时间时完整的,不进行自动填充【 :00 】,直接把日期时间中的 【 T 】 替换为空字符串 &ndash;%&gt;--%>
<%--<c:if test="${timeLength gt generalLength}">--%>
    <%--<c:set var="cleandDateTime" value="${fn:replace(datetime,'T',' ')}"/>--%>
<%--</c:if>--%>
<%--&lt;%&ndash;   解析时间, type="BOTH"为同时解析日期跟时间    &ndash;%&gt;--%>
<%--<fmt:parseDate value="${cleandDateTime}" var="parsedDateTime" pattern="${pattern}" type="BOTH"/>--%>
<%--<fmt:formatDate value="${parsedDateTime}" pattern="${pattern}" type="BOTH"/>--%>