Space_411
=========

Space 411 Android App.

If (Space 411.usedWebVersion == True) {

	Toast.makeText(Space411.this, "Here is the Official Android App!", Toast.LENGHT_LONG).show();

} else { // That is if the it's false; You've never used the Web Version.
		
	// I've never seen a Dialog this descreptive:
	Dialog.show("Space 411 is a service purposely created and developed to feed the public with reliable news about Space" +
	  "and all that comes with Space like Astronauts, Planets, Galaxies, etc." + 
		"A web version of this service is up and running already." +
		"Now, Imagine, all that in your Android Phone! Awesome, right?" +
		"Of course it's awesome! Now here is the official Android* App.";
		
	Dialog.show("NB: * - Supports Android API 10 and above.");

}
