Drop Table If EXISTS Users;
Drop Table If EXISTS Referee;
Drop Table If EXISTS Team;
Drop Table If EXISTS Game;

CREATE TABLE Referee (
    ref_id int NOT NULL PRIMARY KEY,
    name varchar(255) NOT NULL,
    PhoneNumber varchar(15),
    birthday DATE
);

CREATE TABLE Users (
    username varchar(16) NOT NULL PRIMARY KEY,
    password varchar(16) NOT NULL,
    UserID int NOT NULL REFERENCES Referee(ref_id)
);

CREATE TABLE Team (
    team_id int NOT NULL PRIMARY KEY,
    name varchar(255) NOT NULL,
    field varchar(255) NOT NULL
);

CREATE TABLE Game (
    game_id int NOT NULL PRIMARY KEY,
    home_team int NOT NULL REFERENCES Team(team_id),
    guest_team int NOT NULL REFERENCES Team(team_id),
    headRef int NOT NULL REFERENCES Referee(ref_id),
    ref2 int NOT NULL REFERENCES Referee(ref_id),
    ref3 int NOT NULL REFERENCES Referee(ref_id),
    game_date DATE NOT NULL,
    field varchar(255) NOT NULL
);