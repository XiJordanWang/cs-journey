class Link:
    empty = ()

    def __init__(self, value, next=empty):
        assert next is Link.empty or isinstance(next, Link)
        self.value = value
        self.next = next

    def __getitem__(self, i):
        if i == 0:
            return self.value
        else:
            print(i, self)
            return self.next[i - 1]

    def __len__(self):
        print(self)
        return 1 + len(self.next)

    def __repr__(self):
        if self.next is Link.empty:
            next = ""
        else:
            next = ", " + repr(self.next)
        return "Link({0}{1})".format(self.value, next)

    