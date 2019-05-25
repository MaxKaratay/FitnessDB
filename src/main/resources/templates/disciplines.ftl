<#import "parts/common.ftl" as c>

<@c.page>
    <div>Disciplines list</div>
    <p></p>
    <table style="border-spacing: 5px">
       <#list disciplines as discipline>
           <tr>
               <td><span>${discipline.name}</span></td>
               <td><i>Max visitors : ${discipline.maxVisitors}</i></td>
           </tr>
       <#else>
        <div>Disciplines not found !</div>
       </#list>
    </table>
</@c.page>