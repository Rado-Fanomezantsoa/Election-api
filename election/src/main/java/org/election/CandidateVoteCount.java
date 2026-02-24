package org.election;

import java.util.Objects;

public class CandidateVoteCount {
    String candidateName;
    int validVoteCount;

    public CandidateVoteCount(String candidateName, int  validVoteCount) {
        this.candidateName = candidateName;
        this.validVoteCount = validVoteCount;
    }

    public String getCandidateName() {
        return candidateName;
    }

    public void setCandidateName(String candidateName) {
        this.candidateName = candidateName;
    }

    public int getValidVoteCount() {
        return validVoteCount;
    }

    public void setValidVoteCount(int validVoteCount) {
        this.validVoteCount = validVoteCount;
    }

    @Override
    public String toString() {
        return "CandidateVoteCount{" +
                "candidateName='" + candidateName + '\'' +
                ", validVoteCount=" + validVoteCount +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        CandidateVoteCount that = (CandidateVoteCount) o;
        return getValidVoteCount() == that.getValidVoteCount() && Objects.equals(getCandidateName(), that.getCandidateName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCandidateName(), getValidVoteCount());
    }
}
