<%@ page import="java.util.*, com.project.Task" %>
<!DOCTYPE html>
<html>
<head>
    <title>Task Dashboard</title>

<style>
body {
    font-family: 'Segoe UI', sans-serif;
    background: #f5f7fa;
    margin: 0;
    padding: 20px;
}

h2 {
    text-align: center;
    color: #333;
}

.container {
    max-width: 900px;
    margin: auto;
}

.card {
    background: white;
    padding: 20px;
    margin-bottom: 15px;
    border-radius: 10px;
    box-shadow: 0px 4px 10px rgba(0,0,0,0.1);
}

.task-name {
    font-size: 18px;
    font-weight: bold;
    color: #222;
}

.progress-box {
    width: 100%;
    background: #ddd;
    border-radius: 20px;
    overflow: hidden;
    margin-top: 10px;
}

.progress-bar {
    height: 20px;
    color: white;
    text-align: center;
    font-size: 12px;
}

.low { background: #4CAF50; }
.medium { background: #ff9800; }
.high { background: #f44336; }

.delete-btn {
    padding: 6px 12px;
    background: #f44336;
    color: white;
    border: none;
    border-radius: 5px;
    text-decoration: none;
}

.delete-btn:hover {
    background: #d32f2f;
}

.update-btn {
    padding: 6px 12px;
    background: #2196F3;
    color: white;
    border: none;
    border-radius: 5px;
}

.update-btn:hover {
    background: #1976D2;
}

.not-allowed {
    color: gray;
}

.back {
    display: block;
    text-align: center;
    margin-top: 20px;
    text-decoration: none;
    color: #333;
}

/* UPDATE FORM */
.update-form {
    margin-top: 10px;
    background: #f9f9f9;
    padding: 10px;
    border-radius: 8px;
}
</style>

</head>

<body>

<h2>Task Dashboard</h2>

<div class="container">

<%
ArrayList<Task> list = (ArrayList<Task>) request.getAttribute("data");

if (list != null && !list.isEmpty()) {

for (Task t : list) {

    int progress = t.getProgress();
    String colorClass = "high";

    if (progress >= 80) colorClass = "low";
    else if (progress >= 50) colorClass = "medium";
%>

<div class="card">

    <div class="task-name"><%= t.getName() %></div>

    <p>Total Days: <%= t.getTotalDays() %> | Days Passed: <%= t.getDaysPassed() %></p>

    <!-- Progress Bar -->
    <div class="progress-box">
        <div class="progress-bar <%= colorClass %>" style="width:<%= progress %>%">
            <%= progress %>%
        </div>
    </div>

    <p><b>Risk:</b> <%= t.getRisk() %></p>

    <!-- UPDATE FORM -->
    <div class="update-form">
        <form action="UpdateTaskServlet" method="post">
            
            <input type="hidden" name="id" value="<%= t.getId() %>">

            Total Days:
            <input type="number" name="totalDays" value="<%= t.getTotalDays() %>" required>

            Days Passed:
            <input type="number" name="daysPassed" value="<%= t.getDaysPassed() %>" required>

            <input type="submit" value="Update" class="update-btn">

        </form>
    </div>

    <br>

    <% if (progress >= 80) { %>
        <a class="delete-btn" href="DeleteTaskServlet?id=<%= t.getId() %>">
            Delete Task
        </a>
    <% } else { %>
        <span class="not-allowed">Complete more to enable delete</span>
    <% } %>

</div>

<%
}
} else {
%>

<p style="text-align:center;">No Tasks Found</p>

<%
}
%>

</div>

<a href="dashboard.html" class="back">Back to Dashboard</a>

</body>
</html>