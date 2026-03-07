package com.example.tourism.ui.ai

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

data class ChatMessage(
    val text: String,
    val isUser: Boolean,
    val timestamp: Long = System.currentTimeMillis()
)

class AiViewModel : ViewModel() {

    private val welcomeMessage = ChatMessage("Hello! I'm your PearlGuide AI assistant. Ask me anything about traveling in Uganda!", false)

    private val _messages = MutableStateFlow<List<ChatMessage>>(listOf(welcomeMessage))
    val messages: StateFlow<List<ChatMessage>> = _messages

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _suggestedQuestions = MutableStateFlow(listOf(
        "Best time to visit?",
        "How to see Gorillas?",
        "Top food to try",
        "Visa requirements",
        "Adventure in Jinja"
    ))
    val suggestedQuestions: StateFlow<List<String>> = _suggestedQuestions

    fun sendMessage(text: String) {
        if (text.isBlank()) return

        val userMessage = ChatMessage(text, true)
        _messages.value = _messages.value + userMessage
        
        viewModelScope.launch {
            _isLoading.value = true
            
            // Simulate realistic "thinking" and "typing" delay
            delay(1500)
            
            val aiResponse = generateAiResponse(text)
            
            // For a more natural feel, if the response is long, we "type" it in chunks (simulated)
            _messages.value = _messages.value + ChatMessage(aiResponse, false)
            
            _isLoading.value = false
            
            // Update suggestions based on context
            updateSuggestions(text)
        }
    }

    fun clearChat() {
        _messages.value = listOf(welcomeMessage)
        _suggestedQuestions.value = listOf(
            "Best time to visit?",
            "How to see Gorillas?",
            "Top food to try",
            "Visa requirements",
            "Adventure in Jinja"
        )
    }

    private fun updateSuggestions(lastQuery: String) {
        val query = lastQuery.lowercase()
        _suggestedQuestions.value = when {
            query.contains("gorilla") || query.contains("trekking") || query.contains("bwindi") -> 
                listOf("Gorilla permit cost", "Bwindi vs Mgahinga", "What to pack for trekking", "Best time for gorillas")
            
            query.contains("food") || query.contains("eat") || query.contains("rolex") || query.contains("luwombo") -> 
                listOf("What is a Rolex?", "Best restaurants in Kampala", "Try Luwombo", "Is the water safe?")
            
            query.contains("jinja") || query.contains("nile") || query.contains("water") || query.contains("rafting") -> 
                listOf("White water rafting", "Source of the Nile boat trip", "Bungee jumping cost", "Jinja nightlife")
            
            query.contains("visa") || query.contains("entry") || query.contains("passport") || query.contains("requirement") -> 
                listOf("e-Visa application link", "Yellow Fever requirement", "East Africa Tourist Visa", "Visa on arrival?")
            
            query.contains("safari") || query.contains("park") || query.contains("wildlife") || query.contains("animals") -> 
                listOf("Murchison Falls", "Queen Elizabeth Park", "Kidepo Valley", "Tree climbing lions", "Big Five in Uganda")
            
            query.contains("budget") || query.contains("cost") || query.contains("price") || query.contains("cheap") -> 
                listOf("Budget travel tips", "Backpacker hostels", "Public transport (Matatus)", "Local food prices")
                
            query.contains("kampala") || query.contains("city") || query.contains("nightlife") -> 
                listOf("Kampala city tour", "Safe areas in Kampala", "Ndere Cultural Centre", "Museum & Monuments")

            else -> listOf("Murchison Falls", "Cultural sites", "Packing list", "Budget travel tips", "Gorilla trekking")
        }
    }

    private fun generateAiResponse(userQuery: String): String {
        val query = userQuery.lowercase()
        return when {
            query.contains("best time") -> 
                "The absolute best time to visit Uganda is during the dry seasons: **December to February** and **June to August**. These months are perfect for wildlife viewing and gorilla trekking because the trails are drier and animals gather around water sources. However, Uganda is a year-round destination thanks to its tropical climate!"
            
            query.contains("gorilla") || query.contains("trekking") || query.contains("bwindi") -> 
                "Gorilla trekking is a once-in-a-lifetime experience! You can track them in **Bwindi Impenetrable National Park** or **Mgahinga Gorilla National Park**. You'll need a permit (currently $700 for foreign non-residents). I recommend booking at least 6 months in advance as they sell out quickly!"
            
            query.contains("permit") || query.contains("cost") && query.contains("gorilla") -> 
                "Gorilla permits cost:\n• $700 for Foreign Non-Residents\n• $600 for Foreign Residents\n• 250,000 UGX for East African Citizens.\nThis includes park entry, trackers, and armed rangers for your safety."
            
            query.contains("murchison") -> 
                "Murchison Falls is spectacular! It's where the mighty Nile is squeezed through a tiny 7-meter gap, plunging 43 meters down. You can enjoy boat cruises to the base of the falls, hike to the 'Top of the Falls', and see the 'Big Four' (no rhinos here, but you can see them at nearby Ziwa)!"
            
            query.contains("jinja") || query.contains("nile") || query.contains("rafting") -> 
                "Jinja is the 'Adrenaline Capital of East Africa'! It's home to the **Source of the Nile**. You can go Grade 5 white-water rafting, kayaking, bungee jumping, or take a peaceful sunset boat cruise. Don't forget to try the local 'Jinja Rolex' while you're there!"
            
            query.contains("food") || query.contains("eat") || query.contains("rolex") || query.contains("luwombo") -> 
                "You must try the **Rolex** (a rolled chapatti with eggs and veggies)—it's our famous street food! For a traditional meal, try **Luwombo** (stew steamed in banana leaves). Also, don't miss our fresh tilapia from Lake Victoria and the sweet 'Ndizi' (small bananas)."
            
            query.contains("visa") || query.contains("entry") || query.contains("requirement") -> 
                "Most visitors need a visa. You must apply for an **e-Visa** online before arrival at visas.immigration.go.ug. If you're also visiting Kenya and Rwanda, the **East Africa Tourist Visa** ($100) is a great deal and allows multiple entries between the three countries."
            
            query.contains("safety") || query.contains("safe") -> 
                "Uganda is generally very safe for tourists, and Ugandans are known for being exceptionally welcoming. Just use common sense: avoid walking alone at night in big cities, keep an eye on your belongings in crowded places, and follow your guide's advice in national parks."
            
            query.contains("packing") || query.contains("bring") || query.contains("clothes") -> 
                "Pack light! Bring:\n1. Neutral-colored clothes for safaris (avoid blue/black as they attract tsetse flies).\n2. Long sleeves and trousers for trekking.\n3. A good waterproof jacket.\n4. Sturdy hiking boots.\n5. High-SPF sunscreen and insect repellent with DEET."
            
            query.contains("budget") || query.contains("cheap") || query.contains("price") -> 
                "Uganda can be enjoyed on any budget. Backpackers can spend $30-$50/day using 'Matatus' (minibus taxis) and staying in hostels. Mid-range is $100-$200/day, while luxury safaris can exceed $500/day. Eating local food like 'Posho and Beans' or a 'Rolex' is very affordable!"
                
            query.contains("kampala") || query.contains("city") -> 
                "Kampala is a vibrant city that never sleeps! Visit the **Kasubi Tombs**, the **Uganda Museum**, and the **Bahai Temple** (the only one in Africa). For a great view, climb the minaret of the **Gaddafi Mosque**. The nightlife in areas like Kololo and Kabalagala is legendary!"

            query.contains("hello") || query.contains("hi") || query.contains("hey") -> 
                "Hello there! I'm your PearlGuide AI. I can help you with safari bookings, travel tips, or just tell you more about the beautiful 'Pearl of Africa'. What's on your mind today?"
            
            query.contains("thank") -> 
                "You're very welcome! Is there anything else you'd like to know about Uganda? I'm here to help you have the best trip possible!"

            else -> 
                "That's a great question! As your Uganda travel expert, I can tell you that the 'Pearl of Africa' has everything from the snow-capped Rwenzori Mountains to the source of the Nile. Would you like to know more about our **National Parks**, **Cultural Sites**, or **Travel Logistics** (like visas and transport)?"
        }
    }
}
