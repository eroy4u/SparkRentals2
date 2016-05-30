<#macro page_head>
  <title>Spark Rentals</title>
  <link rel="stylesheet" href="/css/default.css">
  <link rel="stylesheet" href="/css/bootstrap.min.css">
  <script src="/js/default.js"></script>

</#macro>

<#macro page_body>
  <h1>Spark Rentals</h1>
</#macro>

<#macro display_page>
<!doctype html>
  <html>
    <head> 
      <@page_head/> 
    </head>
    <body>
      <div class="container">
        <@page_body/>
      </div>
    </body>
  </html>
</#macro>
