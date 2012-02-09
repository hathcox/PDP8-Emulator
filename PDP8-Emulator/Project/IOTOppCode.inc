TITLE IOTOppCode

.code

IOTDeviceSelection PROC uses eax edx
	mov eax , CurrentAddress
	and eax , 000111111000b
	shr eax , 3
	cmp eax , 3d
	je KEYBOARD
	cmp eax , 4d
	je CONSOLE
	jmp UNKNOWN
KEYBOARD:
	call IOTKeyboard
	ret
CONSOLE:
	call IOTConsole
	ret
UNKNOWN:
	mov edx , OFFSET IOTOppCodeMessageOne
	call WriteString
	ret
IOTDeviceSelection ENDP

IOTKeyboard PROC uses eax 
	mov eax , IOTOppCodeCounter
	inc eax 
	mov IOTOppCodeCounter , eax
	ret
IOTKeyboard ENDP

IOTConsole PROC uses eax esi edx
	mov esi , IOTOppCodeCounter
	imul esi , 2
	mov edx , OFFSET IOTOppCodeStr
	add edx , esi
	call WriteString
	ret
IOTConsole ENDP