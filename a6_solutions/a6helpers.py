def find_mean(l,dim):
    sum = 0.0
    for x in l:
        sum += x[dim]
    if len(l) < 1:
        return sum
    avg = sum/len(l)
    return avg

def find_std(l,dim):
    mean = find_mean(l,dim)
    sum = 0.0
    for x in l:
        sum += (x[dim] - mean) ** 2
    if len(l) < 1:
        return sum
    avg = sum/len(l)
    return avg ** 0.5