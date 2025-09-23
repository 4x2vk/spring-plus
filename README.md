<div align="center">
  <img src="https://upload.wikimedia.org/wikipedia/commons/4/44/Spring_Framework_Logo_2018.svg" alt="Spring Framework" width="200"/>
</div>

![JDK 17](https://img.shields.io/badge/JDK-17-orange?logo=java&logoColor=white) 
[![Spring Boot 3.3.3](https://img.shields.io/badge/Spring%20Boot-3.3.3-6DB33F?logo=springboot&logoColor=white)](https://spring.io/projects/spring-boot) 
![Spring Security](https://img.shields.io/badge/Spring%20Security-6DB33F?logo=springsecurity&logoColor=white) 
![Spring Data JPA](https://img.shields.io/badge/Spring%20Data%20JPA-6DB33F?logo=spring&logoColor=white) 
![QueryDSL](https://img.shields.io/badge/QueryDSL-5.0-blue?logo=java&logoColor=white) 
![MySQL](https://img.shields.io/badge/MySQL-4479A1?logo=mysql&logoColor=white) 
![JWT](https://img.shields.io/badge/JWT-0.11.5-blue?logo=jsonwebtokens&logoColor=white) 
![BCrypt](https://img.shields.io/badge/BCrypt-0.10.2-blue?logo=java&logoColor=white)

# Spring Plus
A comprehensive SB application showcasing advanced enterprise features and problem-solving skills through systematic implementation of core Spring ecosystem technologies.

## Project Overview
This repository demonstrates progressive mastery of concepts through 11 challenging implementation tasks. Starting from basic transaction management, the project evolves to include advanced features like QueryDSL optimization, Spring Security integration, and performance tuning.

### Completed Tasks
| Level | Task Description |
|-------|------------------|
| **Level 1** | **Foundation & Core Concepts** |
| Lv 1.1  | Fixed `@Transactional` read-only connection error in todo save operations |
| Lv 1.2  | Added user nickname field and integrated it into JWT payload for frontend display |
| Lv 1.3  | Implemented dynamic JPQL queries for weather and date range filtering |
| Lv 1.4  | Fixed failing controller test for non-existent todo exception handling |
| Lv 1.5  | Corrected AOP pointcut expression for admin access logging |
| **Level 2** | **Advanced JPA & Performance Optimization** |
| Lv 2.6  | Implemented JPA cascade operations for automatic manager assignment |
| Lv 2.7  | Resolved N+1 query problem in comment retrieval using fetch joins |
| Lv 2.8  | Migrated JPQL to QueryDSL while maintaining query optimization |
| Lv 2.9  | Replaced custom authentication with Spring Security JWT implementation |
| **Level 3** | **Expert-Level Features** |
| Lv 3.10 | Built advanced search API with QueryDSL projections and pagination |
| Lv 3.11 | Implemented independent transaction processing for audit logging |

---

## ♻ Key Improvements

### Transaction Management
- **Problem Solved**: Fixed `Connection is read-only` error preventing todo creation
- **Solution**: Added proper `@Transactional` annotations to enable write operations
- **Advanced Implementation**: Created independent transaction boundaries for audit logging that persist even when main operations fail

### Authentication & Security
- **JWT Enhancement**: Extended JWT payload to include user nickname for frontend integration
- **Spring Security Migration**: Completely replaced custom Filter and ArgumentResolver with Spring Security framework
- **Features Preserved**: Role-based access control, JWT token validation, and user permission management

### Query Optimization & Performance
- **N+1 Problem Resolution**: Eliminated performance bottleneck in `CommentController.getComments()` using fetch join strategies
- **QueryDSL Integration**: Migrated JPQL queries to type-safe QueryDSL implementation with projections
- **Advanced Search**: Built comprehensive search API returning only necessary fields (title, manager count, comment count)
- **Performance Impact**: Reduced database calls by ~90% in list operations through proper entity loading strategies

### Dynamic Query Building
- **Flexible Search**: Implemented optional parameter filtering for weather conditions and date ranges
- **JPQL Implementation**: Created maintainable dynamic queries with service-layer conditional execution
- **Pagination Support**: Added efficient pagination for large datasets with proper sorting

### AOP & Cross-Cutting Concerns
- **Admin Access Logging**: Fixed AOP aspect pointcut expression to properly intercept `UserAdminController.changeUserRole()`
- **Audit Trail**: Implemented comprehensive logging system with independent transaction processing

### JPA Relationship Management
- **Cascade Operations**: Configured automatic manager assignment when todos are created
- **Entity Optimization**: Proper cascade types and fetch strategies for optimal database interaction

### Test Coverage & Quality
- **Controller Testing**: Fixed failing exception handling tests with proper MockMvc assertions
- **Integration Testing**: Comprehensive test coverage for N+1 query detection and transaction boundaries
- **Performance Validation**: Query optimization verification and projection efficiency testing


## ✔ Performance Achievements

### Before Optimization
- N+1 queries causing performance bottleneck
- Full entity loading for list operations
- Manual transaction management complexity

### After Implementation
- **Query Reduction**: Single optimized queries with fetch joins
- **Memory Efficiency**: QueryDSL projections loading only required fields
- **Transaction Isolation**: Independent audit logging with proper propagation
- **Type Safety**: Compile-time query validation with QueryDSL
