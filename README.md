# MobileGithubTest

* Overview

This is an Android project which perform REST call in order to get *Github Users* by following pagination through a *load-more* event.

Once the user's data is displayed, the first screen has two options:

 1. _*See in GitHub*_ link which opens a web-browser and redirect you to review the user information in GitHub site
 2. _*See Repositories*_ link which opens a new screen and display all user's repositories
 
A second screen is displayed to review the user's repositories dedatils, in this screen a pagination was implemented by following user's event to *load previous* or *load next* set of data, the app also displays the current page number in bottom of the screen

* Next Steps

This app obtains data from GitHub server, consider implement data persitence by using Room, which is a platform Data-Persistence library built-in

This app was implemented with the MVP known architecture, there is a new set of Architecture-Components in the android platform which we can get advantage from.

