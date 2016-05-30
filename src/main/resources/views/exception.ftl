<#include "base.ftl">


<#macro page_body>
  <h1>Spark Rentals - Add/Update a rental</h1>
  <ul class="nav nav-pills">
    <li><a href="/">Search for rentals</a></li>
    <li class="active"><a href="/">Add/Update a rental</a></li>
  </ul>
    <p>
    Sorry there is eomthing wrong with our system now. Please try again later.
    <#if message??>
      ${message}
    </#if>
    <br/>
    <a href="/add">Click to add/update more</a>
  </p>
</#macro>

<@display_page/>