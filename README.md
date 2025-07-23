# RedBus Search Functionality Assignment 

## Tools used:
- Java - Core programming language for test development
- Selenium WebDriver - For automating browser interactions like selecting cities, applying filters, and scrolling
- ChromeDriver - WebDriver implementation for launching and controlling the Chrome browser
- Maven - Project management tool used to handle dependencies and build the project
- Git - Version control to track and manage code changes

## Objective:<br/>
Automate RedBus Bus Search and Result Extraction using Selenium WebDriver and Java.
Simulate a real user journey on https://www.redbus.in, starting from selecting cities to printing all the available bus operators after applying some filters.

## Instructions: <br/>
- Launch Browser and Open Website:
- Launch the Chrome browser.
- Navigate to: https://www.redbus.in

- Select Source Location (From):
- Click on the "From" location input box.

- Enter: "Mumbai".
- Wait for the auto-suggestions to appear.
- Select "Mumbai" from the suggestions.

- Select Destination Location (To):
- Click on the "To" location input box.
- Enter: "Pune".
- Wait for the auto-suggestions to appear.
- Select "Pune" from the suggestions.

- Search for Buses:
- Click on the "Search Buses" button.

Apply Filters:
- Enable the "Primo" filter option.
- Enable the "Evening" filter option.

Handle Lazy Loading – Scroll Until End of List:
- Keep scrolling down the bus list until the "End of list" message appears.
- Ensure all buses are loaded (handle lazy loading logic).

Extract and Print Bus Operator Names:
- After fully loading the list, extract the names of all visible bus operators.
- Print each operator's name.

Finally, print the total number of buses available after filtering.

## Expected Outcome: <br/>
- Your program should search the buses from Mumbai to Pune
- Select the filters as Primo and Evening
- Perform the scrolling of the list
- Capture the buses' names and print them on the console
