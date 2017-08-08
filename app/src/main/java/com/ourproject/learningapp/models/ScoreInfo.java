package com.ourproject.learningapp.models;

/**
 * Created by Moetaz on 8/7/2017.
 */

public class ScoreInfo {
    private String CompetitorName;
    private String UserScore;
    private String CompetitorScore;
    private String CompetitorId;

    public ScoreInfo(){

    }

    public ScoreInfo(String competitorName, String userScore, String competitorScore,String CompetitorId) {
        CompetitorName = competitorName;
        UserScore = userScore;
        CompetitorScore = competitorScore;
        this.CompetitorId = CompetitorId;
    }

    public void setCompetitorId(String competitorId) {
        CompetitorId = competitorId;
    }

    public String getCompetitorId() {

        return CompetitorId;
    }

    public String getCompetitorName() {
        return CompetitorName;
    }

    public String getUserScore() {
        return UserScore;
    }

    public String getCompetitorScore() {
        return CompetitorScore;
    }

    public void setCompetitorScore(String competitorScore) {
        CompetitorScore = competitorScore;
    }

    public void setUserScore(String userScore) {
        UserScore = userScore;
    }

    public void setCompetitorName(String competitorName) {
        CompetitorName = competitorName;
    }
}
