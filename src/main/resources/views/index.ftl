<#include "base.ftl">

<#macro page_body>
  <h1>Spark Rentals</h1>
  <p>Search for rentals</p>
  <form method="GET" action="/">
    <label>Rental ID</label>
    <input name="id" type="text" value="" class="form-control"/>
    <label>City</label>
    <select name="city">
        
    </select>
    <input type="text" value="" class="form-control"/>
  </form>
  
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
    <p>There are no rentals matching your search criteria.</p>
  </#if>
  
</#macro>

<@display_page/>