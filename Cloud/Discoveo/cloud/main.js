
// Use Parse.Cloud.define to define as many cloud functions as you want.
// For example:

const DISCOVEO = 'Discoveo';
const REVIEW = 'Review';

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

Parse.Cloud.afterSave('Review', function(request) {


    var Discoveo = Parse.Object.extend(DISCOVEO);
    var discoveo = new Discoveo();
    discoveo.id = request.object.get('discoveo').id;

    console.log(request.object.get('discoveo').id);

    var query = new Parse.Query(REVIEW);
    query.equalTo('discoveo', discoveo);
    query.find({
        success: function(results) {
            console.log(results);
            var numberOfObjects = results.length;
            var total = 0;
            for(var i = 0; i < results.length; i++) {
                console.log(results[i].get('rating'));
                total += results[i].get('rating');
            }
            total = total / numberOfObjects;
            console.log("Saving!!!");
            discoveo.save({
                rating: total
            },{
                success: function(object) {

                },
                error: function(object, error) {
                    response.error('Error: ' + error.code)
                }
            });
        }, error: function(error) {

        }
    });
});