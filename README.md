# CSC120-FinalProject

## Deliverables:
 - Your final codebase
 - Your revised annotated architecture diagram
 - Design justification (including a brief discussion of at least one alternative you considered)
 - A map of your game's layout (if applicable)
 - `cheatsheet.md`
 - Completed `rubric.md`
  
## Additional Reflection Questions
 - What was your **overall approach** to tackling this project?
 I tried to set up the chemistt class first. I wrote most of my chemistt methods and even though I couldn't figure out how to run the program for a while, it was really helpful to have all of these functions ready once I got the game loop down. From there, I just tried working on the game loop as much as I could and trying new things and adjusting when they failed. ie, I tried a few different formats for identifying the hoods until I decided that the one I ended up choosing was the best.

 - What **new thing(s)** did you learn / figure out in completing this project?
 I definitely have a much better understanding of the relationships between classes after this project. I didn't fully understand that I had to create new instances of each Room, for example, in order to get the functions from Room, but once I understood that the coding went a lot more smoothly. I also finally understand why overloading methods can be helpful, as I had two rooms that only connected two one other room rather than two, so I needed a new constructor that was the same method, with only one parameter. I think a lot of things made more sense when I was making my own design choices rather than trying to fit to a really specific assignment. 

 I tried to include a timer aspect that the user could input 'stop' when the reaction was over, but was having a lot of issues with it and decided to just make the ending a random result (because sometimes that is how reactions are in real life). 

 - Is there anything that you wish you had **implemented differently**?
Each hood gets a new random inventory each time the game is played, but I don't have any measures ensuring that there is at least one of every necessary item present somewhere in the game. I think it's kind of funny because sometimes you just can't run the reactions because the hoods don't have what you need, and that's definitely reflective of the real life experience. My chemist friends thought that was funny, but it is definitely inconvenient when it happens too much. I increased the upper bound of the items allowed in each hood, but didn't add any checks. I debated over it a lot because I don't think non-chemists have that much fun playing the game anyway, but I decided that the point of the game is to emulate what running a reaction is really like. 

I think organization in general was an issue for me and I would've liked to organize my code better, but that is more on the back end and doesn't really affect how the game is played.

The user doesn't really have an opportunity to add the wrong thing to their reaction and cause it to fail that way, so that would be fun to add as well. 

 - If you had **unlimited time**, what additional features would you implement?
 I want to include a feature for chemists where you are given the entire inventory and then you have to figure out a reaction based on what is available. These reactions I included are really common organic chemistry reactions so I think it would be fun to see if chemists could get it. 

 I think if I had more time/didn't have a bunch of other finals to work on then I would have tried to make the timer work with input. 

 - What was the most helpful **piece of feedback** you received while working on your project? Who gave it to you?
 A lot of people at demo day were having issues with how much information they were receiving each round, so I adjusted the output to make it more user-friedly. I've never designed a text-based game before so it was definitely helpful to see how people were reacting to it. 

 - If you could go back in time and give your past self some **advice** about this project, what hints would you give?
I think I would recommend to write more things out before I started working to get a better sense of what I want each class to do. I mostly figured that out as I went, but I think writing it out and maybe talking to someone else in the class would have been helpful for getting a better grounding before I started coding. 

 - _If you worked with a team:_ please comment on how your **team dynamics** influenced your experience working on this project.
 N/A. 
