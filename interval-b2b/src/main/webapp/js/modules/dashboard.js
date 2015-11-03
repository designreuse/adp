app.controller('DashboardCtrl',
    function ($scope, orderFactory, dashboardFactory) {
        console.log('inside dashboard controller');
        $scope.openOrders = null;
        $scope.inProcessOrders = null;
        $scope.rfdOrders = null;
        $scope.dlvdOrders = null;
        $scope.otherOrders = null;

        var columnDef = [
            {name : 'Order #', field : 'id'},
            {name : 'Total Quantity', field : 'lineItemCount'},
            {name : 'Order Total', field : 'total'},
            {name : 'Screen', field : 'show.screenName'},
            {name : 'Seat #', field : 'seatNo'},
            {name : 'Show Time', field : 'show.time',cellFilter : 'formatTime:row.entity.show.time'},
            {name : 'Action', cellTemplate : 'templates/orderGridAction.html'},
        ];

        $scope.openOrdersGridOpts = {
            columnDefs : columnDef,
            data : $scope.openOrders,
            enableRowSelection: false,
            enableSelectAll: false,
            multiSelect : false,
            name : 'openOrdersGridOpts'
        };

        $scope.inProcessOrdersGridOpts = {
            columnDefs : columnDef,
            data : $scope.inProcessOrders,
            enableRowSelection: false,
            enableSelectAll: false,
            multiSelect : false,
            name : 'inProcessOrdersGridOpts'
        };

        $scope.rfdOrdersGridOpts = {
            columnDefs : columnDef,
            data : $scope.rfdOrders,
            enableRowSelection: false,
            enableSelectAll: false,
            multiSelect : false,
            name : 'rfdOrdersGridOpts'
        };

        $scope.dlvdOrdersGridOpts = {
            columnDefs : columnDef,
            data : $scope.dlvdOrders,
            enableRowSelection: false,
            enableSelectAll: false,
            multiSelect : false,
            name : 'dlvdOrdersGridOpts'
        };

        $scope.otherOrdersGridOpts = {
            columnDefs : columnDef,
            data : $scope.otherOrders,
            enableRowSelection: false,
            enableSelectAll: false,
            multiSelect : false,
            name : 'otherOrdersGridOpts'
        };

        $scope.loadForVendor = function(){
            $scope.loadOpenOrders();
            $scope.loadInProcessOrders();
            $scope.loadRFDOrders();
            $scope.loadDlvdOrders();
            $scope.loadOtherOrders();
        }

        $scope.loadOpenOrders = function(){
            orderFactory.getOrderByCenter({id: $scope.centerId, type: 'center', status : '2'}, function (data) {
                $scope.openOrders = data;
                $scope.openOrdersGridOpts.data = $scope.openOrders;
            });
        }

        $scope.loadInProcessOrders = function(){
            orderFactory.getOrderByCenter({id: $scope.centerId, type: 'center', status : '3'}, function (data) {
                $scope.inProcessOrders = data;
                $scope.inProcessOrdersGridOpts.data = $scope.inProcessOrders;
            });
        }

        $scope.loadRFDOrders = function(){
            orderFactory.getOrderByCenter({id: $scope.centerId, type: 'center', status : '4'}, function (data) {
                $scope.rfdOrders = data;
                $scope.rfdOrdersGridOpts.data = $scope.rfdOrders;
            });
        }

        $scope.loadDlvdOrders = function(){
            orderFactory.getOrderByCenter({id: $scope.centerId, type: 'center', status : '5'}, function (data) {
                $scope.dlvdOrders = data;
                $scope.dlvdOrdersGridOpts.data = $scope.dlvdOrders;
            });
        }

        $scope.loadOtherOrders = function(){
            orderFactory.getOrderByCenter({id: $scope.centerId, type: 'center', status : '7'}, function (data) {
                $scope.otherOrders = data;
                $scope.otherOrdersGridOpts.data = $scope.otherOrders;
            });
        }

        $scope.updateOrderStatus = function(grid, orderId, status){
            console.log('orderId : ', orderId);
            console.log('status : ', status);
            orderFactory.updateStatus({id: orderId, status : status}, null, function (data) {
                $scope.load();
            });
        }

        $scope.loadForAdmin = function(){
            $scope.loadProductCountByCenter();
            $scope.loadOrderCountByCenter();
        }

        $scope.loadProductCountByCenter = function(){
            dashboardFactory.productCountByCenter(null, function (data) {
                var chartData = [{key: "Product per Center", values : data}];
                $scope.createProductsByCenterChart(chartData);
            });
        }

        $scope.loadOrderCountByCenter = function(){
            dashboardFactory.orderCountByCenter(null, function (data) {
                var chartData = [{key: "Orders per Center", values : data}];
                $scope.createOrdersByCenterChart(chartData);
            });
        }

        $scope.createProductsByCenterChart = function(data){
            nv.addGraph(function() {
                var chart = nv.models.discreteBarChart()
                    .x(function(d) { return d.name })
                    .y(function(d) { return d.productCount })
                    .staggerLabels(true)
                    .showValues(true);

                d3.select('#productsByCenter')
                    .datum(data)
                    .transition().duration(500)
                    .call(chart)
                ;

                nv.utils.windowResize(chart.update);

                return chart;
            });
        }

        $scope.createOrdersByCenterChart = function(data){
            nv.addGraph(function() {
                var chart = nv.models.discreteBarChart()
                    .x(function(d) { return d.name })
                    .y(function(d) { return d.orderCount })
                    .staggerLabels(true)
                    .showValues(true);

                d3.select('#ordersByCenter')
                    .datum(data)
                    .transition().duration(500)
                    .call(chart)
                ;

                nv.utils.windowResize(chart.update);

                return chart;
            });
        }

        if($scope.centerId){
            $scope.loadForVendor();
        }else{
            $scope.loadForAdmin();
        }

    }
);

