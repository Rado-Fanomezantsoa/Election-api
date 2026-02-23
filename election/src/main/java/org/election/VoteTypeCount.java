package org.election;

import java.util.Objects;

public class VoteTypeCount {
    private VoteType voteType;
    private int count;
    public VoteTypeCount(VoteType voteType, int count) {
        this.voteType = voteType;
        this.count = count;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public VoteType getVoteType() {
        return voteType;
    }

    public void setVoteType(VoteType voteType) {
        this.voteType = voteType;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        VoteTypeCount that = (VoteTypeCount) o;
        return getCount() == that.getCount() && getVoteType() == that.getVoteType();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getVoteType(), getCount());
    }

    @Override
    public String toString() {
        return "VoteTypeCount{" +
                "count=" + count +
                ", voteType=" + voteType +
                '}';
    }
}

