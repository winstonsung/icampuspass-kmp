// Standard imports
import SwiftUI
import WebKit

struct WebView: UIViewRepresentable {
    typealias UIViewType = WKWebView

    let webView: WKWebView

    func makeUIView(context: Context) -> WKWebView {
        return webView
    }

    func updateUIView(_ uiView: WKWebView, context: Context) {
    }
}

class WebViewModel: ObservableObject {
    let webView: WKWebView

    let url: URL

    @MainActor
    init() {
        webView = WKWebView(frame: .zero)
        url = URL(string: "https://ilifeapp.az.tku.edu.tw/MicrosoftIdentity/Account/SignIn")!
        webView.load(URLRequest(url: url))
    }
}

struct Sandbox5Screen: View {
    @StateObject var viewModel = WebViewModel()

    var body: some View {
        WebView(webView: viewModel.webView)
    }
}

#Preview {
    Sandbox5Screen()
}
