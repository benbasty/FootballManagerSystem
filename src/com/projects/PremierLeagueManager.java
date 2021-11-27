package com.projects;

import jdk.jshell.spi.ExecutionControl;

import java.sql.SQLOutput;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class PremierLeagueManager implements LeagueManager {
    private final int numberOfClubs;
    private final ArrayList<FootBallClub> league;
    private final Scanner scanner;
    private final ArrayList<Match> matches;

    public PremierLeagueManager(int numberOfClubs) {
        this.numberOfClubs = numberOfClubs;
        league = new ArrayList<>();
        this.scanner = new Scanner(System.in);
        matches = new ArrayList<>();

        displayMenu();

    }

    private void displayMenu() {
        while (true) {
            System.out.println("Premier League Menu");
            System.out.println("Create new team and add it to the league (Press 1)");
            System.out.println("Delete existing team from league (Press 2)");
            System.out.println("Display statistics for team (Press 3)");
            System.out.println("Display the Premier League Table (Press 4)");
            System.out.println("Add a played match (Press 5)");
            System.out.println("Display Calendar and Find Match (Press 6)");
            String line = scanner.nextLine();
            int command = 0;
            try {
                command = Integer.parseInt(line);
            } catch (Exception e) {
                System.out.println("Wrong command. Please try again");
            }
            switch (command) {
                case 1:
                    addTeams();
                    break;
                case 2:
                    deleteTeams();
                    break;
                case 3:
                    displayStatistics();
                    break;
                case 4:
                    displayLeagueTable();
                    break;
                case 5:
                    addPlayedMatch();
                    break;
                case 6:
                    displayCalendar();
                    break;
                default:
                    System.out.println("Wrong command");
            }
        }
    }




    private void addTeams() {
        if (league.size() == numberOfClubs) {
            System.out.println("Can't add more clubs to the league");
            return;
        }
        FootBallClub club = new FootBallClub();
        System.out.print("Insert Club Name: ");
        String line = scanner.nextLine();
        club.setName(line);
        if (league.contains(club)) {
            System.out.println("This club is already in the league");
            return;
        }
        System.out.print("Insert club location: ");
        line = scanner.nextLine();
        club.setLocation(line);
        league.add(club);
        System.out.println("Thank you. The club " + club.getName() + " has been added");
    }

    private void deleteTeams() {
        System.out.print("Insert Club Name to be deleted: ");
        String line = scanner.nextLine();
        for (FootBallClub club: league) {
            if (club.getName().equals(line)) {
                league.remove(club);
                System.out.println("Club " + club.getName() + " removed");
                return;
            }
        }
        System.out.println("No such club in league");

    }


    private void displayStatistics() {
        System.out.print("Insert Club Name: ");
        String line = scanner.nextLine();
        for (FootBallClub club: league) {
            if (club.getName().equals(line)) {
                System.out.println("Club " + club.getName() + " matches won: " + club.getWinCount());
                System.out.println("Club " + club.getName() + " matches lost: " + club.getDefeatCount());
                System.out.println("Club " + club.getName() + " matches draw: " + club.getDrawCount());
                System.out.println("Club " + club.getName() + " scored goals: " + club.getScoredGoalsCount());
                System.out.println("Club " + club.getName() + " received goals: " + club.getReceivedGoalsCount());
                System.out.println("Club " + club.getName() + " points: " + club.getPoints());
                System.out.println("Club " + club.getName() + " matches played: " + club.getMatchesPlayed());
                return;
            }
        }
        System.out.println("No such club in the league");
    }

    private void displayLeagueTable() {
        // here we use the custom comparator class
        Collections.sort(league, new CustomComparator());
        for (FootBallClub club: league) {
            System.out.println("Club: " + club.getName() + " #Points: " + club.getPoints() + " #Goal Difference: " + (club.getScoredGoalsCount() - club.getReceivedGoalsCount()));
        }
    }

    private void addPlayedMatch() {
        System.out.print("Enter date (format mm-dd-yyyy) ");
        String line = scanner.nextLine();
        Date date;
        try {
            date = new SimpleDateFormat("MM-dd-yyyy").parse(line);
        } catch (ParseException e) {
            System.out.println("You have to enter the date in the format mm-dd-yyyy");
            return;
        }
        System.out.print("Enter home team: ");
        line = scanner.nextLine();
        FootBallClub home = null;
        for (FootBallClub club : league) {
            if (club.getName().equals(line)) {
                home = club;
            }
        }
        if (home == null) {
            System.out.println("No such club in the league");
            return;
        }
        System.out.print("Enter away team: ");
        line = scanner.nextLine();
        FootBallClub away = null;
        for (FootBallClub club: league) {
            if (club.getName().equals(line)) {
                away = club;
            }
        }
        if (away == null) {
            System.out.println("No such club in the league");
            return;
        }

        System.out.print("Enter home team goals: ");
        line = scanner.nextLine();
        int homeGoals = -1;
        try {
            homeGoals = Integer.parseInt(line);
        } catch (Exception e) {
            System.out.println("Enter a correct number");
        }
        if (homeGoals == -1) {
            System.out.println("You have to enter the number of goals");
            return;
        }

        System.out.print("Enter away team goals: ");
        line = scanner.nextLine();
        int awayGoals = -1;
        try {
            awayGoals = Integer.parseInt(line);
        } catch (Exception e) {
            System.out.println("Enter a correct number");
        }
        if (awayGoals == -1) {
            System.out.println("You have to enter the number of goals");
            return;
        }

        Match match = new Match();
        match.setDate(date);
        match.setTeamA(home);
        match.setTeamB(away);
        match.setTeamAScore(homeGoals);
        match.setTeamBScore(awayGoals);
        matches.add(match);
        home.setScoredGoalsCount(home.getScoredGoalsCount() + homeGoals);
        away.setScoredGoalsCount(away.getScoredGoalsCount() + awayGoals);
        home.setReceivedGoalsCount(home.getReceivedGoalsCount() + homeGoals);
        away.setReceivedGoalsCount(away.getReceivedGoalsCount() + awayGoals);
        home.setMatchesPlayed(home.getMatchesPlayed() + 1);
        away.setMatchesPlayed(away.getMatchesPlayed() + 1);

        if (homeGoals > awayGoals) {
            home.setPoints(home.getPoints() + 3);
            home.setWinCount(home.getWinCount() + 1);
            away.setDefeatCount(away.getDefeatCount() + 1);
        } else if (homeGoals < awayGoals) {
            away.setPoints(away.getPoints() + 3);
            away.setWinCount(away.getWinCount() + 1);
            home.setDefeatCount(home.getDefeatCount() + 1);
        } else {
            home.setPoints(home.getPoints() + 1);
            away.setPoints(away.getPoints() + 1);
            home.setDrawCount(home.getDrawCount() + 1);
            away.setDrawCount(away.getDrawCount() + 1);
        }



    }

    private void displayCalendar() {
        System.out.println("Enter year: ");
        String line = scanner.nextLine();
        int Y = -7777;
        try {
            Y = Integer.parseInt(line);
        } catch (Exception e) {
            if (Y == -7777) {
                System.out.println("You have to enter a year");
                return;
            }

        }
        System.out.println("Enter month: ");
        line = scanner.nextLine();
        int M = 0;
        try {
            M = Integer.parseInt(line);
        } catch (Exception e) {
            if (M == 0) {
                System.out.println("You have to enter a month");
                return;
            }

        }

        String[] months = {
                "",
                "January", "February", "March",
                "April","May","June",
                "July","August","September",
                "October","November","December"
        };

        int[] days = { 0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        if (M == 2 && isLeapYear(Y)) days[M] = 29;
        System.out.println("    " + months[M] + " " + Y);
        System.out.println("Sun   Mon   Tue  Wed   Thu  Fri   Sat");
        int d = day(M, 1, Y);
        String space = "";
        for (int i = 0; i < d; i++) {
            System.out.println("    ");
        }
        for (int i = 1; i <= days[M]; i++) {
            if (i < 10) {
                System.out.println(i + "    ");
            } else {
                System.out.println(i + "  ");
                if (((i + d) % 7 == 0) || (i == days[M]) ){
                    System.out.println();
                }
            }

        }
        System.out.println("Enter day: ");
        line = scanner.nextLine();
        int D = 0;
        try {
            D = Integer.parseInt(line);
        } catch (Exception e) {

        }
        if (D == 0 || days[M] < D) {
            System.out.println("You have to enter a day in a month");
            return;
        }
        Calendar cal = Calendar.getInstance();
        cal.set(Y, M-1, D);
        for (Match m: matches) {
            Calendar cal2 = Calendar.getInstance();
            cal2.setTime(m.getDate());
            if (cal.get(Calendar.YEAR) == cal2.get(Calendar.YEAR) || cal.get(Calendar.DAY_OF_YEAR) == cal2.get(Calendar.DAY_OF_YEAR)) {
                System.out.println(m.getTeamA().getName() + " " + m.getTeamAScore() + " : " + m.getTeamBScore() + " " + m.getTeamB().getName());
            }
        }


    }

    public int day(int M, int D, int Y) {
        int y = Y - (14 - M) / 12;
        int x = y + y/4 - y/100 + y/400;
        int m = M + 12 * ((14 - M) / 12) - 2;
        int d = (D + x + (31 * m) / 12) % 7;
        return d;
    }

    public boolean isLeapYear(int year) {
        if ((year % 4 == 0) && (year % 100 != 0)) {
            return true;
        }
        if (year % 400 == 0) return true;
        return false;
    }



}
