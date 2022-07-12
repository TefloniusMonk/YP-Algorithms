#include <iostream>
#include <string>
#include <unordered_map>
#include <set>
#include <algorithm>
#include <deque>
#include <vector>


using namespace std;

string if_graph_full();

const int WHITE = -1;
const int BLACK = 1;
const int GRAY = 0;

int main() {
    cout << if_graph_full() << endl;
    return 0;
}


string if_graph_full() {
    int vertexes, edges;
    cin >> vertexes >> edges;
    unordered_map<int, vector<int> > graph;
    for (int i = 0; i < edges; i++) {
        int from, to;
        cin >> from >> to;
        vector<int> adjacent;
        if (graph.find(from) != graph.end()) {
            graph.at(from).push_back(to);
        } else {
            adjacent.push_back(to);
            graph.insert(pair<int, vector<int> >(from, adjacent));
        }
    }

    int visited[vertexes + 1];
    for (int i = 1; i <= vertexes; i++) {
        visited[i] = WHITE;
    }

    deque<int> resultStack;

    for (int i = 1; i <= vertexes; i++) {
        if (visited[i] != WHITE) {
            continue;
        }
        deque<int> deque;
        deque.push_front(i);

        while (!deque.empty()) {
            auto vert = deque.front();
            deque.pop_front();
            if (visited[vert] == BLACK) {
                continue;
            }

            if (visited[vert] == GRAY) {
                visited[vert] = BLACK;
                resultStack.push_front(vert);
                continue;
            }

            visited[vert] = GRAY;

            deque.push_front(vert);

            if (graph.find(vert) != graph.end()) {
                for (int vertex: graph.at(vert)) {
                    if (visited[vertex] == WHITE) {
                        deque.push_front(vertex);
                    }
                }
            }
        }
    }
    string res;
    while (!resultStack.empty()) {
        res += to_string(resultStack.front()) + " ";
        resultStack.pop_front();
    }
    return res;
}

