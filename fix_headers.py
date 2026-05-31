import re

with open('src/main/App.java', 'r', encoding='utf-8') as f:
    content = f.read()

lines = content.split('\n')

def pad_to_84(s):
    """Pad visible text to exactly 84 chars by adding trailing spaces."""
    # Remove surrounding quotes
    if s.startswith('"') and s.endswith('"'):
        inner = s[1:-1]
        if len(inner) < 84:
            inner = inner + ' ' * (84 - len(inner))
        elif len(inner) > 84:
            # For Grandis border with 87 ?s, don't pad here
            pass
        return '"' + inner + '"'
    return s

# Line ranges for each displayMenu
ranges = {
    'displayMenuValerion': (413, 424),
    'displayMenuAsgard': (427, 440),
    'displayMenuGrandis': (443, 457),
    'displayMenuLumina': (460, 474),
    'displayMenuAldoria': (477, 488),
}

# Fix borders and pad content
modified_lines = []
for i, line in enumerate(lines):
    line_num = i + 1  # 1-indexed
    
    # Fix Grandis border: 87 ? -> 86 ?
    if 'GRN_BORDER' in line and '"???????????????????????????????????????????????????????????????????????????????????????"' in line:
        line = line.replace(
            '"???????????????????????????????????????????????????????????????????????????????????????"',
            '"??????????????????????????????????????????????????????????????????????????????????????"'
        )
        print(f"Fixed Grandis border at line {line_num}")
    
    # For content lines between borders in city menus, pad to 84 chars
    # Check if this line is within any city displayMenu and is a content line
    in_menu = False
    menu_name = None
    for name, (start, end) in ranges.items():
        if start <= line_num <= end:
            in_menu = True
            menu_name = name
            break
    
    if in_menu:
        # This is a content line in a city menu
        # Match Java string literal concatenation pattern: + "content" + 
        # We want to find the main content string (not the border ? strings)
        
        # Strategy: find string literals between border markers
        if 'ANSI_BOLD + "' in line or 'ANSI_RESET + "' in line:
            # Extract the content string - look for "..." before + BORDER_VAR
            # Pattern: (color_var) + ANSI_BOLD + "content" + BORDER_VAR + ANSI_BOLD + "?"
            
            # Find the content inside quotes that's between ANSI_BOLD + " and " + COLOR_VAR
            # Actually, let's use a simpler approach: find all quoted strings
            
            def fix_quoted_content(match):
                full = match.group(0)
                # Extract the content between quotes
                inner = match.group(1)
                if len(inner) < 84 and '?' not in inner.replace(' ', ''):
                    # It's a text/art line, pad it
                    inner = inner + ' ' * (84 - len(inner))
                    return '"' + inner + '"'
                elif len(inner) < 84 and '?' in inner:
                    # It might be a description line with ? for decorative purposes
                    # Only pad if it has spaces
                    pass
                return match.group(0)
            
            # Actually this is getting complex. Let me just handle known lines.
            pass
    
    modified_lines.append(line)

# Single-pass fix: for specific line numbers, pad the content
fixes = {
    # Grandis border (87? -> 86?) - already handled above
    
    # Grandis art lines
    447: 2,   # 82->84
    448: 5,   # 79->84
    449: 1,   # 83->84
    450: 3,   # 81->84
    451: 1,   # 83->84
    
    # Grandis desc lines
    454: 2,   # 82->84
    455: 2,   # 82->84
    456: 4,   # 80->84
    
    # Lumina art lines (465-468)
    465: 2,   # 82->84
    466: 1,   # 83->84
    467: 3,   # 81->84
    468: 3,   # 81->84
    
    # Lumina desc lines
    471: 3,   # 81->84
    472: 1,   # 83->84
    473: 3,   # 81->84
    
    # Asgard art lines
    431: 1,   # 83->84
    432: 2,   # 82->84
    433: 1,   # 83->84
    434: 1,   # 83->84
    435: 1,   # 83->84
    
    # Aldoria art lines (all 82->84)
    481: 2,
    482: 2,
    483: 2,
    484: 2,
    485: 2,
    486: 2,
}

for lineno, pad_count in fixes.items():
    idx = lineno - 1
    line = modified_lines[idx]
    # Find the content string: it's the quoted text between color codes
    # Pattern: "...text..." - we need to add `pad_count` spaces before closing quote
    # The content string is a Java string literal inside the concatenation
    
    # Strategy: find the last " before the closing + BORDER + "?"
    # Actually, let's look at the typical pattern:
    # COLOR + ANSI_BOLD + "content" + SOFT_TEAL + ANSI_BOLD + "?"
    # We need to add padding to "content"
    
    # Find the content string - it's between ANSI_BOLD + " and " + COLOR
    # Simple approach: find ALL quoted strings and pad the longest one (the content)
    # that doesn't contain only spaces
    
    # Actually, let me find quoted strings in the line
    quotes = re.finditer(r'"([^"]*)"', line)
    candidates = []
    for q in quotes:
        text = q.group(1)
        if len(text) > 10 or '?' in text or len(text.strip()) > 5:
            candidates.append((q.start(), q.end(), text, q.group(0)))
    
    if candidates:
        # Pick the candidate that has '?' or longest non-space content
        best = None
        for start, end, text, full in candidates:
            non_space = text.replace(' ', '')
            if '?' in non_space or len(non_space) > 10 or (len(text.strip()) > 20):
                best = (start, end, text, full)
                break
        # Fallback: longest string
        if best is None:
            best = max(candidates, key=lambda x: len(x[2]))
        
        start, end, text, full = best
        new_text = text + ' ' * pad_count
        new_full = '"' + new_text + '"'
        modified_lines[idx] = line[:start] + new_full + line[end:]
        print(f"  Fixed line {lineno}: '{text}' -> '{new_text}' (len {len(text)}->{len(new_text)})")

result = '\n'.join(modified_lines)
with open('src/main/App.java', 'w', encoding='utf-8') as f:
    f.write(result)

print("\nDone!")
