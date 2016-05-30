<#include "base.ftl">


<#macro page_body>
  <h1>Spark Rentals</h1>
  <ul class="nav nav-pills">
    <li class="active"><a href="/">Search for rentals</a></li>
    <li><a href="/add">Add/Update a rental</a></li>
  </ul>
  <#-- search form -->
  <#include 'search_form.ftl'>

   
  <#-- search results table -->
  <#if rentalList?size gt 0>
    <table class="table">
      <tr>
        <th>ID</th>
        <th>city</th>
        <th>province</th>
        <th>country</th>
        <th>zip code</th>
        <th>type</th>
        <th>has air condition</th>
        <th>has garden</th>
        <th>has pool</th>
        <th>is close to beach</th>
        <th>daily price</th>
        <th>currency</th>
        <th>number of rooms</th>
      </tr>
      <#list rentalList as rental>
        <tr>
          <td>${rental.id}</td>
          <td>${rental.city}</td>
          <td>${rental.province}</td>
          <td>${rental.country}</td>
          <td>${rental.zipCode}</td>
          <td>${rental.type}</td>
          <td>${rental.hasAirCondition?string('Yes', 'No')}</td>
          <td>${rental.hasGarden?string('Yes', 'No')}</td>
          <td>${rental.hasPool?string('Yes', 'No')}</td>
          <td>${rental.isCloseToBeach?string('Yes', 'No')}</td>
          <td>${rental.dailyPrice}</td>
          <td>${rental.currency}</td>
          <td>${rental.roomsNumber}</td>
        </tr>
      </#list>
    </table>
  <#else>
    <#if errorMessages?size gt 0>
      <p>There are some errors in your input</p>
      <ul>
        <#list errorMessages as msg>
          <li>${msg}</li>
        </#list>
      </ul>
      
    <#else>
      <p>There are no rentals matching your search criteria.</p>
    </#if>
  </#if>
  
</#macro>

<@display_page/>