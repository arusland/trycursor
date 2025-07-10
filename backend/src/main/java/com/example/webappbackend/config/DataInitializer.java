package com.example.webappbackend.config;

import com.example.webappbackend.model.User;
import com.example.webappbackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {
    
    @Autowired
    private UserRepository userRepository;
    
    @Override
    public void run(String... args) throws Exception {
        // Only initialize if no users exist
        if (userRepository.count() == 0) {
            initializeSampleData();
        }
    }
    
    private void initializeSampleData() {
        User[] sampleUsers = {
            new User("John Doe", "john.doe@example.com", "Admin"),
            new User("Jane Smith", "jane.smith@example.com", "User"),
            new User("Bob Johnson", "bob.johnson@example.com", "Manager"),
            new User("Alice Brown", "alice.brown@example.com", "User"),
            new User("Charlie Wilson", "charlie.wilson@example.com", "Admin"),
            new User("Diana Davis", "diana.davis@example.com", "User"),
            new User("Edward Miller", "edward.miller@example.com", "Manager"),
            new User("Fiona Garcia", "fiona.garcia@example.com", "User"),
            new User("George Martinez", "george.martinez@example.com", "User"),
            new User("Helen Rodriguez", "helen.rodriguez@example.com", "Admin")
        };
        
        for (User user : sampleUsers) {
            userRepository.save(user);
        }
        
        System.out.println("Sample data initialized with " + sampleUsers.length + " users");
    }
} 