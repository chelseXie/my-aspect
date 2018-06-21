package com.xiehao.aspect.annotation;

import java.util.Map;

/**
 * Created by next on 2018/6/21.
 */
public class AspectDes {
    private String matchingExpression;
    private boolean hasBefore;
    private boolean hasAfter;
    private boolean hasAround;
    private boolean hasAfterReturning;
    private Map<String,String> wovenMethods;
    private Object aspectObj;

    public String getMatchingExpression() {
        return matchingExpression;
    }

    public void setMatchingExpression(String matchingExpression) {
        this.matchingExpression = matchingExpression;
    }

    public boolean isHasBefore() {
        return hasBefore;
    }

    public void setHasBefore(boolean hasBefore) {
        this.hasBefore = hasBefore;
    }

    public boolean isHasAfter() {
        return hasAfter;
    }

    public void setHasAfter(boolean hasAfter) {
        this.hasAfter = hasAfter;
    }

    public boolean isHasAround() {
        return hasAround;
    }

    public void setHasAround(boolean hasAround) {
        this.hasAround = hasAround;
    }

    public boolean isHasAfterReturning() {
        return hasAfterReturning;
    }

    public void setHasAfterReturning(boolean hasAfterReturning) {
        this.hasAfterReturning = hasAfterReturning;
    }

    public Object getAspectObj() {
        return aspectObj;
    }

    public void setAspectObj(Object aspectObj) {
        this.aspectObj = aspectObj;
    }

    public Map<String, String> getWovenMethods() {
        return wovenMethods;
    }

    public void setWovenMethods(Map<String, String> wovenMethods) {
        this.wovenMethods = wovenMethods;
    }
}
