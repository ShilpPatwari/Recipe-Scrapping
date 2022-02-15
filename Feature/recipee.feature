

Feature: Web scrape tarladalal website

Background: User is in homepage


Scenario Outline: User search for a recipe
Given User visits tarladalal website home page
When User enters https://tarladalal.com and search "<cuisine>"
Then page with recipe title should be displayed

Examples:
| cuisine |
| Punjabi | 
| Gujarati |
| South Indian |
| Chinese |
