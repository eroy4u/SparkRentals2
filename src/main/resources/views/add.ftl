<#include "base.ftl">


<#macro page_body>
  <h1>Spark Rentals - Add/Update a rental</h1>
  <ul class="nav nav-pills">
    <li><a href="/">Search for rentals</a></li>
    <li class="active"><a href="/">Add/Update a rental</a></li>
  </ul>
  
    <#if errorMessages?? && errorMessages?size gt 0>
    <p>There are some errors in your input</p>
    <ul>
      <#list errorMessages as msg>
        <li>${msg}</li>
      </#list>
    </ul>
    </#if>
  <#-- search form -->
  <form method="POST" action="/add">
    <div class="row">
      <div class="col-sm-4">
        <label>Rental ID</label>
        <input type="text" name="id" value="<#if data["id"]??>${data["id"]?html}</#if>" class="form-control"/>
      </div>
      <div class="col-sm-4">
        <label>Number of rooms</label>
        <input type="number" name="roomsNumber" value="<#if data["roomsNumber"]??>${data["roomsNumber"]?html}</#if>" class="form-control"/>
      </div>
      <div class="col-sm-4">
        <label>Daily price</label>
        $<input type="numer" step="0.01" name="dailyPrice" value="<#if data["dailyPrice"]??>${data["dailyPrice"]?html}</#if>" class="form-control"/>
      </div>
      
    </div>
  
    <#include 'rental_form_upper_rows.ftl'>
    
    <div class="row">
      <div class="col-sm-4">
        * If a record with the same rental ID already exists, the existing record will be updated.
      </div>
    </div>
    <div class="row">
      <div class="col-sm-4">
        <button type="submit" class="btn btn-primary form-control">Add/Update</button>
      </div>
    </div>
  </form>
</#macro>

<@display_page/>