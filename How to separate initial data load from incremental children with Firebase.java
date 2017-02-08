// Since child_added events for the initial, pre-loaded data will fire before the value event fires on the parent, you can use the value event as a sort of "initial data loaded" notification.
var initialDataLoaded = false;
var ref = new Firebase('https://<your-Firebase>.firebaseio.com');

ref.on('child_added', function(snapshot) {
  if (initialDataLoaded) {
    var msg = snapshot.val().msg;
    // do something here
  } else {
    // we are ignoring this child since it is pre-existing data
  }
});

ref.once('value', function(snapshot) {
  initialDataLoaded = true;
});

// Thankfully, Firebase will smartly cache this data, meaning that creating both a child_added 
// and a value listener will only download the data one time. 
// Adding new Firebase listeners for data which has already crossed the wire is extremely cheap 
// and you should feel comfortable doing things like that regularly.

// Or one add a timestamp to the children with Firebase.ServerValue.TIMESTAMP, this will be trivial 
// with startAt