package com.example.compose.rally

import androidx.compose.ui.test.hasContentDescription
import androidx.compose.ui.test.hasParent
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onRoot
import androidx.compose.ui.test.printToLog
import com.example.compose.rally.ui.components.RallyTabRow
import com.example.compose.rally.ui.theme.RallyTheme
import org.junit.Rule
import org.junit.Test

class TopAppBarTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun rallyTopAppBarTest() {
        val allScreens = rallyTabRowScreens
        composeTestRule.setContent {
            RallyTheme {
                RallyTabRow(
                    allScreens = allScreens,
                    onTabSelected = {

                    },
                    currentScreen = Accounts
                )
            }
        }
        /**
         * property merging and the merged and unmerged Semantics trees
         *
         */
       // composeTestRule.onRoot().printToLog("currentLabelExists")
//        composeTestRule
//            .onNodeWithContentDescription("overview")
//            .assertIsSelected()
        composeTestRule.onRoot(useUnmergedTree = true).printToLog("currentLabelExists")
//        composeTestRule
//            .onNodeWithText(RallyScreen.Overview.name.uppercase())
//            .assertExists()

        composeTestRule
            .onNode(
                hasText(RallyScreen.Accounts.name.uppercase()) and
                        hasParent(
                            hasContentDescription(RallyScreen.Accounts.name.lowercase())
                        ),
                useUnmergedTree = true
            )
            .assertExists()
    }

}