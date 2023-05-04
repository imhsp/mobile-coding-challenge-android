# Mobile Developer Coding Challenge

Please read the instructions below carefully before starting the coding challenge.

Once submitted, the mobile team will review your work and get back to you as soon as possible.

## The Goal

You will be building a simple two-screen podcasts app. A basic mockup is provided below:

[![](https://i.imgur.com/yi8w1s8.png)](https://i.imgur.com/yi8w1s8.png)

Screenshot of implementation
Screen 1             |  Screen 2
:-------------------------:|:-------------------------:
![](/screenshots/screen_1.png)(/screenshots/screen_1.png) | ![](/screenshots/screen_2.png)(/screenshots/screen_2.png)

#### Screen 1

- [x] Show a list of podcasts using the endpoint provided below.
- [x] Each list item should show the podcast thumbnail, title, and publisher name.
- [x] Leave some space for the "Favourited" label (refer to the second podcast in the list in the mockup above).
- [x] Show the Favourited label only if the podcast has been favourited, otherwise hide the label.

#### Screen 2

- [x] Tapping on a list item from Screen 1 should bring you to Screen 2.
- [x] On Screen 2, show the podcast's title, publisher name, thumbnail, and description.
- [x] Add a Favourite button.
- [x] The Favourite button should have two states: Favourite and Favourited.
- [x] When tapping the Favourite button, the label should change to Favourited, and vice-versa.

## Details

- [x] Fork this repo and keep it public until we've been able to review it.
- [x] Can be written in either Java or Kotlin for Android applicants, and Objective-C or Swift for iOS applicants.
- [x] For the API, use data provided by Listen Notes:
	 - [x] Use the following endpoint to fetch podcast data: https://www.listennotes.com/api/docs/?lang=kotlin&test=1#get-api-v2-best_podcasts
	 - [x] No API key required, you can simply use the mock server to fetch test data. [More information here](https://www.listennotes.help/article/48-how-to-test-the-podcast-api-without-an-api-key "More information here").
- [x] Focus on implementing the app in portrait orientation only.
- [x] The list should support pagination, loading 10 items at a time.
- [x] Favourite state should be persistent.

## The Evaluation

Your code will be evaluated based on the following criteria:

- [ ] The code should compile.
- [ ] No crashes, bugs, or compiler warnings.
- [ ] App operates as outlined above.
- [ ] Conforms to modern development principles.
- [ ] Code is easy to understand. Bonus points for documentation.
- [ ] Commit history is consistent, easy to follow and understand.