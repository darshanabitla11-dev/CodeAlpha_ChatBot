import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class ChatBot extends JFrame implements ActionListener {

    JTextArea chatBox;
    JTextField userInput;
    JButton sendButton;
    Random random;

    String[] greetReplies = {
        "Hello! How can I help you?",
        "Hi there! What do you want to know?",
        "Hey! Ask me anything about the college."
    };

    String[] admissionReplies = {
        "Admissions start in June. You can apply on the college website with your 12th marksheet.",
        "To get admission you need your 12th marksheet, ID proof and the application fee receipt.",
        "Eligibility is usually 50% in 12th, but it can change depending on the course."
    };

    String[] feesReplies = {
        "Fees can be paid online through the student portal or at the accounts office.",
        "Fees are usually between 40k to 90k per year depending on the course.",
        "Scholarships are available for students scoring above 85 percent, check with the admin office."
    };

    String[] libraryReplies = {
        "The library is open from 8 AM to 8 PM on all working days.",
        "You can borrow up to 3 books at a time for 14 days.",
        "Library remains closed on public holidays."
    };

    String[] examReplies = {
        "Exam timetable is uploaded on the notice board one month before exams.",
        "Results are usually declared within 30 days after the exam ends.",
        "You need at least 75 percent attendance to be allowed to sit for exams."
    };

    String[] hostelReplies = {
        "Hostel facility is available with mess included for both boys and girls.",
        "Hostel rooms are given on first come first serve basis after admission.",
        "Mess fees are charged separately every semester."
    };

    String[] thanksReplies = {
        "You're welcome!",
        "No problem, happy to help!",
        "Anytime!"
    };

    String[] confusedReplies = {
        "Sorry, I did not understand. Try asking about admission, fees, library, exams or hostel.",
        "Can you ask that differently? I only know about admission, fees, library, exams and hostel.",
        "Not sure about that. Try asking about fees, exams, library or admission."
    };

    public ChatBot() {

        setTitle("My College Chatbot");
        setSize(450, 550);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        random = new Random();

        chatBox = new JTextArea();
        chatBox.setEditable(false);
        chatBox.setLineWrap(true);
        chatBox.setWrapStyleWord(true);
        chatBox.setFont(new Font("Arial", Font.PLAIN, 14));

        JScrollPane scroll = new JScrollPane(chatBox);

        userInput = new JTextField();
        sendButton = new JButton("Send");

        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new BorderLayout());
        bottomPanel.add(userInput, BorderLayout.CENTER);
        bottomPanel.add(sendButton, BorderLayout.EAST);

        add(scroll, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);

        sendButton.addActionListener(this);
        userInput.addActionListener(this);

        chatBox.append("Bot: Hello! Ask me about admission, fees, library, exams or hostel.\n");
        chatBox.append("Bot: Type bye to close the chat.\n\n");
    }

    public void actionPerformed(ActionEvent e) {

        String message = userInput.getText();

        if (message.trim().equals("")) {
            return;
        }

        chatBox.append("You: " + message + "\n");

        String reply = getReply(message);
        chatBox.append("Bot: " + reply + "\n\n");

        userInput.setText("");
        chatBox.setCaretPosition(chatBox.getDocument().getLength());

        if (message.equalsIgnoreCase("bye") || message.equalsIgnoreCase("exit")) {
            sendButton.setEnabled(false);
            userInput.setEnabled(false);
        }
    }

    public String getReply(String msg) {

        msg = msg.toLowerCase().trim();
        msg = msg.replaceAll("[^a-z0-9 ]", "");

        String[] words = msg.split("\\s+");

        if (containsAny(words, "bye", "exit", "quit")) {
            return "Goodbye! Have a nice day.";
        }
        else if (containsAny(words, "thank", "thanks", "thankyou")) {
            return pickRandom(thanksReplies);
        }
        else if (containsAny(words, "hi", "hello", "hey")) {
            return pickRandom(greetReplies);
        }
        else if (containsAny(words, "admission", "admissions", "apply", "eligibility", "enroll", "enrollment")) {
            return pickRandom(admissionReplies);
        }
        else if (containsAny(words, "fee", "fees", "payment", "scholarship", "cost")) {
            return pickRandom(feesReplies);
        }
        else if (containsAny(words, "library", "book", "books")) {
            return pickRandom(libraryReplies);
        }
        else if (containsAny(words, "exam", "exams", "examination", "result", "results", "marks", "timetable")) {
            return pickRandom(examReplies);
        }
        else if (containsAny(words, "hostel", "room", "mess", "accommodation")) {
            return pickRandom(hostelReplies);
        }
        else {
            return pickRandom(confusedReplies);
        }
    }

    public boolean containsAny(String[] words, String... keywords) {
        for (String w : words) {
            for (String k : keywords) {
                if (w.equals(k)) {
                    return true;
                }
            }
        }
        return false;
    }

    public String pickRandom(String[] options) {
        int index = random.nextInt(options.length);
        return options[index];
    }

    public static void main(String[] args) {
        ChatBot bot = new ChatBot();
        bot.setVisible(true);
    }
}