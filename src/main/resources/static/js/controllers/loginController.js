// Creating the Angular Controller
app.controller('loginController', function($http, $scope,$state,$cookies,$filter,crAcl,OAuth) {

		// method for login
		$scope.user=[];

	$scope.login = function () {

		var options = {
			secure: true
		};
		//recuperer l'access token depuis le serveur d'autoriation si l'authntifaction pass par succés
		OAuth.getAccessToken($scope.user, options).then(function (result) {
			/*	console.log("user", $scope.user);
				console.log("access token==>>>>", result);*/
			$cookies.put("access_token",result.data.access_token);
			$cookies.put("expiry_date",result.data.expires_in);
			$cookies.put("refresh_token",result.data.refresh_token);
				//OAuthToken.getAccessToken();
			//console.log("getcookies------->>>><",$cookies.get("access_token"));
				$scope.getUserByUsername($scope.user.username);
		})
			.catch(function(error) {
			console.log("coordonnee incorrect", error);
			$scope.message = 'Authetication Failed !';
		});
		/*OAuth.getRefreshToken().then(function(result){
			$cookies.putObject("refresh_token",result.data)
		});*/


	};
	$scope.getUserByUsername=function(username){
		$http.get('http://localhost:8081/guest/user/'+username)
			.success(function(data){
				$scope.user=data;
				$cookies.putObject("user_info", $scope.user);
				console.log("role", $cookies.getObject("user_info").role);
				console.log("user",$scope.user);
				$scope.redirection();
			})
			.error(function(err){
				console.log(err)
			});
	};

	$scope.redirection=function(){
		$scope.role = $cookies.getObject("user_info").role;
			if($scope.role.role=="ROLE_ADMIN") {
				crAcl.setRole("ROLE_ADMIN");
				$state.go('helloAdmin');
			}
			else{
				crAcl.setRole("ROLE_USER");
				$state.go('helloUser');
			}
		};
	$scope.checkIfLogged=function(){
		if($cookies.getObject("user_info")===undefined)
			$cookies.putObject("user_info",null);
		if($cookies.getObject("user_info")!==null) {
			/*var date=new Date().getTime();
			console.log("date",date);*/
			$scope.role = $cookies.getObject("user_info").role;
			crAcl.setRole($scope.role.role);
				/*if($scope.role.role=="ROLE_ADMIN") {
					crAcl.setRole("ROLE_ADMIN");
					$state.go('helloAdmin');
				}
			else
					$state.go('helloUser');*/
			}
		else
			crAcl.setRole("ROLE_GUEST");

	};
	$scope.refreshToken=function() {
		console.log("expiry_date",$cookies.get("expiry_date"));
		if ($cookies.get("expiry_date") !== null && $cookies.get("expiry_date")==59 ) {
			console.log("date expiré");
			$http.post('http://localhost:8081/oauth/token?grant_type=refresh_token&refresh_token=' + $cookies.get("refresh_token"))
				.success(function (data) {
					$cookies.put("access_token", data.access_token);
					console.log("access_token",$cookies.get("access_token"))
					$cookies.put("expiry_date",data.expires_in);
				})
				.error(function (err) {
					console.log(err)
				});
		}
	};
	$scope.refreshToken();
	$scope.checkIfLogged();

	/*$scope.insertToken=function(){
		if ($cookies.get("access_token")!==null && $cookies.get("access_token")!==undefined ) {
			$http.defaults.headers.common['Authorization'] = "Bearer " + $cookies.get("access_token");
		}
	};
	$scope.insertToken();*/


});


