app.controller('DashboardCtrl',
    function ($scope, orderFactory) {
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

        $scope.load = function(){
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

        $scope.load();

    }
);

