use jngu;
select * from performance ;
select performance.StartTime, performance.EndTime, performance.SeatLeft, concert.MovieName ,concert.Thumbnail,concert.Description,
concert.Rating,venue.VenueName,TicketVenuePrices.TicketPrice,TicketTypes.SeatName from performance inner join concert on 
performance.concertID = concert.ID  inner join TicketVenuePrices on TicketVenuePrices.performanceID = performance.ID
inner join venue on performance.venueID = venue.id inner join TicketTypes on TicketVenuePrices.ticketTypeID = TicketTypes.id
where concert.MovieName="Black Cab" and venue.venueName = "Pinnacle Bank Arena";
select * from venue where VenueName ="Pinnacle Bank Arena";
select  * from venue;

select * from performance inner join concert on performance.concertID = concert.ID;
select performance.StartTime, performance.EndTime,performance.seatLeft,concert.MovieName,concert.Thumbnail,
concert.Rating,venue.VenueName, TicketVenuePrices.TicketPrice
from performance inner join concert on performance.concertID = concert.ID inner join venue
on performance.venueID = venue.id inner join TicketVenuePrices on TicketVenuePrices.performanceID=performance.ID
where concert.MovieName = "black Cab";

-- insert into venue --
insert into venue (venueName,Address,City,State,PostalCode)values("Lied Center", "301 N 12th St","Lincoln","NE","68508"); 
insert into venue (venueName,Address,City,State,PostalCode)values("Pinnacle Bank Arena", "400 Pinnacle Arena Dr","Lincoln","NE","68508"); 
insert into venue (venueName,Address,City,State,PostalCode)values("The Bourbon Theatre", "1415 O St","Lincoln","NE","68508"); 

-- -insert into concert --
insert into concert(MovieName,Description,Thumbnail,Rating) values("Scorpion","live concert by Drake","pics/drake.jpg",5);
insert into concert(MovieName,Description,Thumbnail,Rating) values("Black Cab","live concert by Higher Brother","pics/higherBrother.jpg",5);
insert into concert(MovieName,Description,Thumbnail,Rating) values("KOD Tour","live concert by JCole","pics/jcole.jpg",5);

-- insert into performance --
insert into performance(StartTime,EndTime,SeatLeft,concertID,venueID) values("2019-03-03 7:00:00","2019-03-03 11:00:00",100,1,1);
insert into performance(StartTime,EndTime,SeatLeft,concertID,venueID) values("2019-03-13 19:00:00","2019-03-13 21:00:00",22,2,2);
insert into performance(StartTime,EndTime,SeatLeft,concertID,venueID) values("2019-03-21 21:00:00","2019-03-22 00:00:00",111,3,3);
insert into performance(StartTime,EndTime,SeatLeft,concertID,venueID) values("2019-03-21 15:00:00","2019-03-22 18:00:00",101,2,1);

-- insert into TicketTypes --
insert into TicketTypes(SeatName,TicketTypescol)values("Executive","100 level");
-- insert into TicketVenuePrices --
insert into TicketVenuePrices(TicketPrice,venueID,ticketTypeID,performanceID)values(800,1,1,1);
insert into TicketVenuePrices(TicketPrice,venueID,ticketTypeID,performanceID)values(1020,2,1,3);
insert into TicketVenuePrices(TicketPrice,venueID,ticketTypeID,performanceID)values(550,2,1,2);
insert into TicketVenuePrices(TicketPrice,venueID,ticketTypeID,performanceID)values(888,1,1,4);
