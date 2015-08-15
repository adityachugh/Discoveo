
// Use Parse.Cloud.define to define as many cloud functions as you want.
// For example:

const DISCOVEO = 'Discoveo';

Parse.Cloud.define("hello", function(request, response) {
    response.success("Hello world!");
});

Parse.Cloud.define("getDiscoveosNearby", function(request, response) {
    //Pre: location
    //Post: array of discoveos

    var location = request.params.location;

    var query = new Parse.Query(DISCOVEO);
    query.withinKilometers('location', 1.0, location);
    query.find({
        success: function(results) {
            response.success(results);
        }, error: function(error) {
            response.error('Error: ' + error.code)

        }
    });
});