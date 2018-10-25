var app = angular.module("DemoApp", []);
var ctrl = app.controller("CountryController", function($scope, $http, $filter) {
	$scope.isLoading = "is loading ...";
	$scope.sortCol = "id";
	$scope.sortReverse = false;
	$scope.selectedCountry = {};
	$scope.addOrEdit = "add";
	$scope.searchTerm = "";

	$scope.load = function() {
		$http.get("/countries").then(function(response) {
			$scope.countries = response.data;
			$scope.isLoading = "";
		});			
	};

	$scope.sort = function(sortCol) {
		$scope.sortCol = sortCol;
		$scope.sortReverse = !$scope.sortReverse;
	};

	$scope.addOrEditCountry = function(type, country) {
		if (type === "add") {
			$scope.addCountry();
		} else if (type === "edit") {
			$scope.editCountry(country);
		} else {
			console.log(type + " not defined");
		}
	};
	
	$scope.setToAdd = function() {
		$scope.addOrEdit = "add";
	};

	$scope.setEditedCountry = function(country) {
		$scope.addOrEdit = "edit";
		$scope.selectedCountry = country;
	};

	$scope.addCountry = function() {
		var country = $scope.selectedCountry;
		$http.post("country", country).success(function(result) {
			toastr.success("Saved " + country.id + " (" + country.name + ")");
			$scope.selectedCountry = {};
			$scope.load();
		});
	};

	$scope.editCountry = function() {
		var country = $scope.selectedCountry;
		$http.put("country", country).success(function(result) {
			toastr.success("Updated " + country.id + " (" + country.name + ")");
			$scope.selectedCountry = {};
			$scope.load();
		});
	};

	$scope.deleteCountry = function(country) {
		if (window.confirm("Delete " + country.id + "?")) {
			$http.delete("country/" + country.id).success(function(result) {
				toastr.error("Deleted " + country.id + " (" + country.name + ")");
				$scope.load();
			});
		}
	};
	
	$scope.load();
});