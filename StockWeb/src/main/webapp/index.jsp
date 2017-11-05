<!DOCTYPE HTML>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>Payara Micro Stock Ticker</title>
    
    <link href="css/index.css" rel="stylesheet"/>

    <script type="text/javascript" src="jquery-1.8.1.js"></script>
    <script type="text/javascript" src="js/index.js"></script>
    <script type="text/javascript">
        $(document).ready(function () {
            openSocket("${pageContext.request.contextPath}");
        });
    </script>
  </head>
  <body>
    <script type="text/javascript" src="js/highcharts.js"></script>
    <script type="text/javascript" src="js/modules/exporting.js"></script>

    <h1>Stock Graphs</h1>
    
    <div id="container" style="width: 80%; height: 200px; margin: 0 auto">
    </div>

  </body>
</html>
