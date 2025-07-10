package com.example.webappbackend.dto;

import java.util.List;
import java.util.Map;

public class DashboardStats {
    private int totalUsers;
    private int activeUsers;
    private double averageAge;
    private List<ChartData> userGrowth;
    private Map<String, Integer> roleDistribution;
    private List<RecentActivity> recentActivities;
    
    public DashboardStats() {}
    
    public DashboardStats(int totalUsers, int activeUsers, double averageAge, 
                         List<ChartData> userGrowth, Map<String, Integer> roleDistribution,
                         List<RecentActivity> recentActivities) {
        this.totalUsers = totalUsers;
        this.activeUsers = activeUsers;
        this.averageAge = averageAge;
        this.userGrowth = userGrowth;
        this.roleDistribution = roleDistribution;
        this.recentActivities = recentActivities;
    }
    
    // Getters and Setters
    public int getTotalUsers() {
        return totalUsers;
    }
    
    public void setTotalUsers(int totalUsers) {
        this.totalUsers = totalUsers;
    }
    
    public int getActiveUsers() {
        return activeUsers;
    }
    
    public void setActiveUsers(int activeUsers) {
        this.activeUsers = activeUsers;
    }
    
    public double getAverageAge() {
        return averageAge;
    }
    
    public void setAverageAge(double averageAge) {
        this.averageAge = averageAge;
    }
    
    public List<ChartData> getUserGrowth() {
        return userGrowth;
    }
    
    public void setUserGrowth(List<ChartData> userGrowth) {
        this.userGrowth = userGrowth;
    }
    
    public Map<String, Integer> getRoleDistribution() {
        return roleDistribution;
    }
    
    public void setRoleDistribution(Map<String, Integer> roleDistribution) {
        this.roleDistribution = roleDistribution;
    }
    
    public List<RecentActivity> getRecentActivities() {
        return recentActivities;
    }
    
    public void setRecentActivities(List<RecentActivity> recentActivities) {
        this.recentActivities = recentActivities;
    }
    
    public static class ChartData {
        private String label;
        private int value;
        
        public ChartData() {}
        
        public ChartData(String label, int value) {
            this.label = label;
            this.value = value;
        }
        
        public String getLabel() {
            return label;
        }
        
        public void setLabel(String label) {
            this.label = label;
        }
        
        public int getValue() {
            return value;
        }
        
        public void setValue(int value) {
            this.value = value;
        }
    }
    
    public static class RecentActivity {
        private String action;
        private String user;
        private String timestamp;
        
        public RecentActivity() {}
        
        public RecentActivity(String action, String user, String timestamp) {
            this.action = action;
            this.user = user;
            this.timestamp = timestamp;
        }
        
        public String getAction() {
            return action;
        }
        
        public void setAction(String action) {
            this.action = action;
        }
        
        public String getUser() {
            return user;
        }
        
        public void setUser(String user) {
            this.user = user;
        }
        
        public String getTimestamp() {
            return timestamp;
        }
        
        public void setTimestamp(String timestamp) {
            this.timestamp = timestamp;
        }
    }
} 