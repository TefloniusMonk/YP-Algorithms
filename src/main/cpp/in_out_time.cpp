#include <iostream>
#include <string>
#include <unordered_map>
#include <set>
#include <algorithm>
#include <deque>


using namespace std;

string in_out_time();

const int WHITE = -1;
const int BLACK = 1;
const int GRAY = 0;

int main() {
    cout << in_out_time() << endl;
    return 0;
}


string in_out_time() {
    int vertexes, edges;
    cin >> vertexes >> edges;
    unordered_map<int, set<int, greater<int> > > graph;
    for (int i = 0; i < edges; i++) {
        int from, to;
        cin >> from >> to;
        if (graph.find(from) != graph.end()) {
            graph.at(from).insert(to);
        } else {
            set<int, greater<int> > adjacent;
            adjacent.insert(to);
            graph.insert(pair<int, set<int, greater<int> > >(from, adjacent));
        }
    }


    int visited[vertexes + 1];
    for (int i = 1; i < vertexes + 1; i++) {
        visited[i] = WHITE;
    }

    int time = -1;
    int start_node = 1;
    deque<int> deque;
    deque.push_front(start_node);
    string res;
    string result[vertexes + 1];
    while (!deque.empty()) {
        auto vert = deque.front();
        deque.pop_front();
        if (visited[vert] == BLACK) {
            continue;
        }

        if (visited[vert] == GRAY) {
            result[vert] += " " + to_string(++time);
            visited[vert] = BLACK;
            continue;
        }

        visited[vert] = GRAY;
        result[vert] = to_string(++time);

        deque.push_front(vert);

        if (graph.find(vert) != graph.end()) {
            for (int vertex: graph.at(vert)) {
                if (visited[vertex] == WHITE) {
                    deque.push_front(vertex);
                }
            }
        }

    }
    for (int i = 1; i <= vertexes; i++) {
        res += result[i] + "\n";
    }
    return res;
}

