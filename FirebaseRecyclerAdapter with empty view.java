//  FirebaseRecyclerAdapter is backed by a FirebaseArray which is populated using a ChildEventListener 
//  I would add a Single value event listener on the same database reference used to populate your 
//  FirebaseRecyclerAdapter

//create database reference that will be used for both the
//FirebaseRecyclerAdapter and the single value event listener
dbRef = FirebaseDatabase.getInstance().getReference();

//setup FirebaseRecyclerAdapter
mAdapter = new FirebaseRecyclerAdapter<Model, YourViewHolder>(
                Model.class, R.layout.your_layout, YourViewHolder.class, dbRef) {

                @Override
                public void populateViewHolder(YourViewHolder holder, Model model, int position){

                     //your code for populating each recycler view item

                };

mRecyclerView.setAdapter(mAdapter);

//add the listener for the single value event that will function
//like a completion listener for initial data load of the FirebaseRecyclerAdapter
dbRef.addListenerForSingleValueEvent(new ValueEventListener() {
             @Override
             public void onDataChange(DataSnapshot dataSnapshot) {
                 //onDataChange called so remove progress bar

                 //make a call to dataSnapshot.hasChildren() and based 
                 //on returned value show/hide empty view 

                 //use helper method to add an Observer to RecyclerView    
             }

             @Override
             public void onCancelled(DatabaseError databaseError) {

             }
      });


// That would handle the initial setup of the RecyclerView. When onDataChange is called on the single value 
// event listener use a helper method to add an observer to the FirebaseRecyclerAdapter to handle any subsequent 
// additions/deletions to database location.

mObserver = new RecyclerView.AdapterDataObserver() {
            @Override
            public void onItemRangeInserted(int positionStart, int itemCount) {
                //perform check and show/hide empty view
            }

            @Override
            public void onItemRangeRemoved(int positionStart, int itemCount) {
                //perform check and show/hide empty view
            }
           };
mAdapter.registerAdapterDataObserver(mObserver);