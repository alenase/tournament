# tournament
Java Backend Engineer Test Task
_____________________________________________________________________
Preparation
1. Create a MySQL Database and set user and password in pom.xml ( for FlyWay) and in application.proprties
2. Migrate tables into Database using Maven Flyway plugin (for instance)

_____________________________________________________________________
Let's test the application. Here is the chronologic that can be sticked to:
1. Create a tournament
2. Create participants
3. Assign participants to a tournament
4. Start tournament. Matches are generated and 2 participants are added to a single match. If an odd quantity of participants is added, then the last participant remains without match. This last team will be added to the next qualification match. I have created a controller that let adding a match to the tournament, but the quantity of adding match should not exceed matchQuantity (that is generated basing on maxParticipants quantity).
Also I used Set for a list of participants in Tournament class to be sure that one participant is added only one time.
5. Get tournament information
6. Get match information
7. Add further match

1.Create a tournament
POST
http://localhost:8080/tournament

{
    "name": "Test tournament",
    "maxParticipants": 8
}

2. Create participants (one participant can be created at a time)
Create Participant
POST
http://localhost:8080/participants
{
    "name": "Participant1"
}
_____

{
    "name": "Participant2"
}

_____

{
    "name": "Participant3"
}

_____

{
    "name": "Participant4"
}

_____

{
    "name": "Participant5"
}
_____

{
    "name": "Participant6"
}

_____

{
    "name": "Participant7"
}



3. Assign participants to a tournament(id of tournament and participants are required for that! )
Let's assume that tournament id - 2, participants' id - [2..8]
Add participants to a tournament
PUT
http://localhost:8080/tournament/2/add-participants/
[
    {"id": 2},
	{"id": 3},
	{"id": 4},
	{"id": 5},
	{"id": 6},
	{"id": 7},
	{"id": 8}
]

4. Start tournament. Let's assume that tournament id - 2
POST
http://localhost:8080/tournament/2/start

5. Get tournament information. Let's assume that tournament id - 2
GET 
http://localhost:8080/tournament/2

6. Get match results. Let's assume that match id - 2
GET
http://localhost:8080/match/2

7. Add match
POST
http://localhost:8080/tournament/2/create-match
{
    "startTime": null,
    "finishTime": null,
    "participant1Score": 123,
    "participant2Score": 123,
    "participantsId": [5, 4]
}



_____________________________________________________________________
Available controllers:
_____________________________________________________________________

-- Create Tournament
POST
http://localhost:8080/tournament

{
    "name": "Test 2",
    "maxParticipants": 8
}


-- Get Tournament
GET 
http://localhost:8080/tournament/2



-- Add participants to a tournament
PUT
http://localhost:8080/tournament/2/add-participants/
[
    {"id": 6},
	{"id": 7},
	{"id": 8}
]


-- Remove a participant from a tournament
http://localhost:8080/tournament/2/remove-participant
 {"id": 25}
 
 
-- Add new match to the tournament
POST
http://localhost:8080/tournament/2/create-match
{
    "startTime": null,
    "finishTime": null,
    "participant1Score": 123,
    "participant2Score": 123,
    "participantsId": [5, 9]
}
_____________________________________________________________________

-- Create Participant
POST
http://localhost:8080/participants
{
    "name": "Participant1"
}


-- Get Participant by id
GET
http://localhost:8080/participants/8


_______________________________________________________________
Match
-- Get match results
GET
http://localhost:8080/match/2
Reply:
{
    "id": 2,
    "startTime": null,
    "finishTime": null,
    "participant1Score": 0,
    "participant2Score": 0,
    "participants1": "Participant1234",
    "participants2": "Participant123",
    "result": "This match has not been played yet"
}


