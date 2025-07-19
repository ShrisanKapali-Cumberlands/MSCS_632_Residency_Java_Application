# 📝 Interactive To-Do List CLI

A robust, interactive, command-line To-Do list application built entirely in **Java**. This project provides a multi-user, state-based task management system that runs smoothly in any terminal, with a polished and user-friendly text interface.

---

# 👥 Team Members

- Shrisan kapali
- Gaurab Karki
- Kaustubh Rajendra Rajput
- Bikram Rumba
- Shivam Trivedi

---

## 📌 Overview

This CLI app allows **multiple users** to manage their personal tasks independently. It features:

- A clear, menu-driven interface.
- Well-formatted tables and menus.
- A smooth startup animation for a professional touch.

---

## ⭐ Features

### 👥 Multi-User Support

- **Create Users:** Add new users with unique usernames.
- **Switch Users:** Seamlessly switch between existing users.
- Each user has an isolated task list.

### ✅ Complete Task Management

- **Add Tasks:** Create tasks with a description and category.
- **Remove Tasks:** Delete tasks by unique ID.
- **Complete Tasks:** Mark tasks as `COMPLETED`.

### 💻 Interactive CLI

- Intuitive, state-based menus guide users through all actions.
- Tasks are displayed in clean, easy-to-read tables.

### 🔒 Thread-Safe & Concurrent

- Uses `ConcurrentHashMap` for safe multi-user task operations.
- Reliable even with concurrent access.

### ✨ Polished User Experience

- Styled menus and boxed layouts.
- Well-formatted output.
- Engaging welcome banner and loading animation.

---

## ⚙️ Tech Specs

| Item             | Details                                                          |
| ---------------- | ---------------------------------------------------------------- |
| **Language**     | Java                                                             |
| **Java Version** | 17+                                                              |
| **Libraries**    | Standard Java SE only (no external dependencies)                 |
| **Files**        | `Task.java`, `User.java`, `TodoListManager.java`, `TodoApp.java` |

---

## 📂 File Structure

| File                     | Description                                                  |
| ------------------------ | ------------------------------------------------------------ |
| **Task.java**            | Data model for individual tasks (ID, description, status).   |
| **User.java**            | Data model for users with unique usernames.                  |
| **TodoListManager.java** | Core logic: manages users, tasks, and ensures thread safety. |
| **TodoApp.java**         | Entry point: handles CLI interaction and output.             |

---

## 🚀 Getting Started

### ✅ Prerequisites

- **Java Development Kit (JDK)** — version 17 or newer.
- **Visual Studio Code (VS Code)**.
- **Extension Pack for Java** (by Microsoft).

---

### 🔧 Setup Instructions

1. **Create Project**

   - Open VS Code → `Ctrl+Shift+P` → `Java: Create Java Project` → Select `No build tools`.
   - Choose a folder & name your project (e.g., `InteractiveTodoCLI`).

2. **Add Files**

   - In the VS Code Explorer, right-click `src` → `New File`.
   - Create: `Task.java`, `User.java`, `TodoListManager.java`, `TodoApp.java`.
   - Copy and paste the corresponding code into each file.

3. **Run the App**
   - Open `TodoApp.java` in VS Code.
   - Click `Run` above `public static void main(String[] args)`.
   - The Java Extension will compile and launch the app.
   - Use the **TERMINAL** panel for interaction.

---

## 🕹️ How to Use

1. **Startup**

   - A welcome banner and loading animation appear on launch.

2. **User Menu**

   - If no users exist → create one.
   - If users exist → select an existing user to log in.

3. **Task Menu**

   - Add, remove, or complete tasks.
   - View your tasks (`View My Tasks`) or all tasks (`View All Tasks`).
   - Switch user at any time (`Switch User`).

4. **Exit**
   - From the User Menu, choose `Exit` to close the app with a goodbye message.

---
