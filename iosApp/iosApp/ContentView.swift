// Standard imports
import SwiftUI

// Local imports
import Shared

struct ContentView: View {
    @State private var showContent = false

    var body: some View {
        VStack {
            Button(String(localized: "contentview_click_me", defaultValue: "Click me!")) {
                withAnimation {
                    showContent = !showContent
                }
            }

            if showContent {
                VStack(spacing: 16) {
                    Image(systemName: "swift")
                        .font(.system(size: 200))
                        .foregroundColor(.accentColor)

                    Text("SwiftUI: \(Greeting().greet())")
                }
                .transition(.move(edge: .top).combined(with: .opacity))
            }
        }
        .frame(maxWidth: .infinity, maxHeight: .infinity, alignment: .top)
        .padding()
    }
}

#Preview {
    ContentView()
}
