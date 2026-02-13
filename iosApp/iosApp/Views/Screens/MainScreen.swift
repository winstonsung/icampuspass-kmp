// Standard imports
import SwiftUI

struct MainScreen: View {
    @State private var showContent = false

    var body: some View {
        VStack {
            Button(String(localized: "mainscreen_click_me", defaultValue: "Click me!")) {
                withAnimation {
                    showContent = !showContent
                }
            }

            if showContent {
                VStack(spacing: 16) {
                    Image(systemName: "globe")
                        .font(.system(size: 200))
                        .foregroundColor(.accentColor)

                    Text(String(localized: "mainscreen_hello_world", defaultValue: "Hello, world!"))
                }
                .transition(.move(edge: .top).combined(with: .opacity))
            }
        }
        .frame(maxWidth: .infinity, maxHeight: .infinity, alignment: .top)
        .padding()
    }
}

#Preview {
    MainScreen()
}
