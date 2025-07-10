package com.example.webappbackend.service;

import com.example.webappbackend.dto.DashboardStats;
import com.example.webappbackend.model.User;
import com.example.webappbackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class DashboardService {
    
    @Autowired
    private UserRepository userRepository;
    
    public DashboardStats getDashboardStats() {
        List<User> allUsers = userRepository.findAll();
        
        // Calculate basic stats
        int totalUsers = allUsers.size();
        int activeUsers = (int) allUsers.stream()
                .filter(user -> user.getUpdatedAt().isAfter(LocalDateTime.now().minusDays(30)))
                .count();
        
        // Generate mock user growth data
        List<DashboardStats.ChartData> userGrowth = generateUserGrowthData();
        
        // Calculate role distribution
        Map<String, Integer> roleDistribution = allUsers.stream()
                .collect(Collectors.groupingBy(User::getRole, Collectors.collectingAndThen(
                        Collectors.counting(), Long::intValue)));
        
        // Generate recent activities
        List<DashboardStats.RecentActivity> recentActivities = generateRecentActivities(allUsers);
        
        return new DashboardStats(totalUsers, activeUsers, 28.5, userGrowth, roleDistribution, recentActivities);
    }
    
    private List<DashboardStats.ChartData> generateUserGrowthData() {
        List<DashboardStats.ChartData> growthData = new ArrayList<>();
        String[] months = {"Jan", "Feb", "Mar", "Apr", "May", "Jun"};
        int[] values = {120, 150, 180, 220, 280, 350};
        
        for (int i = 0; i < months.length; i++) {
            growthData.add(new DashboardStats.ChartData(months[i], values[i]));
        }
        
        return growthData;
    }
    
    private List<DashboardStats.RecentActivity> generateRecentActivities(List<User> users) {
        List<DashboardStats.RecentActivity> activities = new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd, HH:mm");
        
        // Get recent users and create activities
        List<User> recentUsers = users.stream()
                .sorted(Comparator.comparing(User::getCreatedAt).reversed())
                .limit(5)
                .collect(Collectors.toList());
        
        String[] actions = {"User registered", "Profile updated", "Login successful", "Settings changed", "Data exported"};
        
        for (int i = 0; i < recentUsers.size(); i++) {
            User user = recentUsers.get(i);
            String action = actions[i % actions.length];
            String timestamp = user.getCreatedAt().format(formatter);
            
            activities.add(new DashboardStats.RecentActivity(action, user.getName(), timestamp));
        }
        
        return activities;
    }
    
    public Map<String, Object> getAnalyticsData() {
        Map<String, Object> analytics = new HashMap<>();
        
        // User statistics
        analytics.put("totalUsers", userRepository.countTotalUsers());
        analytics.put("usersThisMonth", 45);
        analytics.put("growthRate", 12.5);
        
        // Performance metrics
        analytics.put("averageResponseTime", 245);
        analytics.put("uptime", 99.8);
        analytics.put("errorRate", 0.2);
        
        // Chart data
        analytics.put("monthlyGrowth", generateMonthlyGrowthData());
        analytics.put("userActivity", generateUserActivityData());
        
        return analytics;
    }
    
    private List<Map<String, Object>> generateMonthlyGrowthData() {
        List<Map<String, Object>> data = new ArrayList<>();
        String[] months = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
        int[] values = {100, 120, 150, 180, 220, 280, 320, 380, 420, 480, 520, 580};
        
        for (int i = 0; i < months.length; i++) {
            Map<String, Object> point = new HashMap<>();
            point.put("month", months[i]);
            point.put("users", values[i]);
            data.add(point);
        }
        
        return data;
    }
    
    private List<Map<String, Object>> generateUserActivityData() {
        List<Map<String, Object>> data = new ArrayList<>();
        String[] days = {"Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"};
        int[] values = {85, 92, 78, 95, 88, 65, 72};
        
        for (int i = 0; i < days.length; i++) {
            Map<String, Object> point = new HashMap<>();
            point.put("day", days[i]);
            point.put("active", values[i]);
            data.add(point);
        }
        
        return data;
    }
} 