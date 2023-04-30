package de.nik1q.wkonverter.models

import android.widget.TextView

class LastUpdatedHelper {
    companion object {
        fun updateLastUpdated(dateString: String, textView: TextView) {
            textView.setText("Letzte Aktualisierung: $dateString")
        }
    }
}


