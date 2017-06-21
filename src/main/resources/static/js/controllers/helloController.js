// Creating the Angular Controller
app.controller('helloController', function($http, $scope,$state,$cookies,crAcl) {
/*console.log("refresh_token",$cookies.get("refresh_token"))
console.log("expiry_date",$cookies.get("expiry_date"))*/
 // method for logout
    $scope.logout = function () {
        $http({

            method: 'DELETE',
            url: "http://localhost:8081/oauth/revoke-token",

            headers: {
                "Authorization": "Bearer " + $cookies.get("access_token"),
                "Content-type": "application/x-www-form-urlencoded; charset=utf-8"
            }})
            .success(function(data){
                crAcl.setRole("ROLE_GUEST");
                var empty=null
                $cookies.putObject("user_info", empty);
                $cookies.put("access_token",empty);
                $cookies.put("refresh_token",empty);
                $cookies.put("expiry_date",empty);
                $state.go('login');
                $scope.message = 'Successfully logged out';
            })
        .error(function(err){
            console.log(err)
        });

    };
    console.log("role",$cookies.getObject("user_info").role.role)
    /*$scope.checkIfLogged=function(){
        if($cookies.getObject("user_info")!==null) {
            $scope.roles = $cookies.getObject("user_info").roles;
            crAcl.setRole("ROLE_USER");
            for(var i=0;i<$scope.roles.length;i++){
                if($scope.roles[i].role=="ADMIN") {
                    crAcl.setRole("ROLE_ADMIN");
                    break;
                }
            };
        }
        else
            crAcl.setRole("ROLE_GUEST");

    };*/
    $scope.listUsers=function(){
        $http.get("http://localhost:8081/api/users"+'?access_token=' + $cookies.get("access_token"))
            .success(function(data){
                $scope.users=data;
                console.log("users",$scope.users);
            })
            .error(function(err){
                console.log(err)
            });
    };
    //$scope.checkIfLogged();



});
