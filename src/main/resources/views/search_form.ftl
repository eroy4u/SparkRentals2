  <form method="GET" action="/" id="id_search_form">
    
    <#include 'rental_form_upper_rows.ftl'>
    <input id="id_page" type="hidden" name="page" value=""/>
    <div class="row">
      <div class="col-sm-4">
        <label>Number of rooms</label>
        <p>From
          <input name="roomsNumberFrom" type="number" value="<#if data["roomsNumberFrom"]??>${data["roomsNumberFrom"]?html}</#if>" class="form-control short-input"/>
          to
          <input name="roomsNumberTo" type="number" value="<#if data["roomsNumberTo"]??>${data["roomsNumberTo"]?html}</#if>" class="form-control short-input"/>
        </p>
      </div>
      <div class="col-sm-4">
        <label>Daily Price</label>
        <p>From
          <input name="dailyPriceFrom" type="number" step="0.01" value="<#if data["dailyPriceFrom"]??>${data["dailyPriceFrom"]?html}</#if>" class="form-control short-input"/>
          to
          <input name="dailyPriceTo" type="number" step="0.01" value="<#if data["dailyPriceTo"]??>${data["dailyPriceTo"]?html}</#if>" class="form-control short-input"/>
        </p>
      </div>
      <div class="col-sm-4">
        <label>Time period</label>
        <p>Created or updated in the last
          <input name="timePeriod" type="number" value="<#if data["timePeriod"]??>${data["timePeriod"]?html}</#if>" class="form-control short-input"/> days
        </p>
      </div>
    </div>
    
    <div class="row">
      <div class="col-sm-4">
        <button type="submit" class="btn btn-primary form-control">Search</button>
      </div>
    </div>
  </form>